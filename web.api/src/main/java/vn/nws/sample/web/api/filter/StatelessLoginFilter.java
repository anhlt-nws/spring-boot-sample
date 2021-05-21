package vn.nws.sample.web.api.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.nws.sample.service.common.ErrorCode;
import vn.nws.sample.service.response.Response;
import vn.nws.sample.web.api.auth.AppUserDetailsService;
import vn.nws.sample.web.api.auth.AuthUserDto;
import vn.nws.sample.web.api.auth.TokenAuthService;
import vn.nws.sample.web.api.auth.UserAuthentication;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
	Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

	/** The token authentication service. */
	private final TokenAuthService tokenAuthenticationService;

	/** The user service. */
	private final AppUserDetailsService userDetailService;
	
	/** The mapper. */
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Instantiates a new stateless login filter.
	 *
	 * @param urlMapping the url mapping
	 * @param tokenAuthenticationService the token authentication service
	 * @param userDetailService the user detail service
	 * @param authenticationManager the authentication manager
	 * @param userService the user service
	 */
	public StatelessLoginFilter(String urlMapping, TokenAuthService tokenAuthenticationService,
			AppUserDetailsService userDetailService, AuthenticationManager authenticationManager) {
		super(urlMapping);
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.userDetailService = userDetailService;
		setAuthenticationManager(authenticationManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			return null;
		}
		final AuthUserDto user = this.toUser(request);
		final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
		return getAuthenticationManager().authenticate(loginToken);
	}

	/**
	 * To user.
	 *
	 * @param request
	 *            the request
	 * @return the admin user detail
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private AuthUserDto toUser(HttpServletRequest request) throws IOException {
		return new ObjectMapper().readValue(request.getInputStream(), AuthUserDto.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		final UserDetails authenticatedUser = this.userDetailService.loadUserByUsername(authResult.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
		try {
			this.tokenAuthenticationService.addJwtTokenToResponse(response, userAuthentication);
//			this.userService.loginSuccessHandle(authResult.getName());
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			logger.info("Authentication Fail");
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}


	/**
	 * Unsuccessful authentication.
	 *
	 * @param request the request
	 * @param response the response
	 * @param failed the failed
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter out = response.getWriter();
		// Handle bad credential and locked result
		if (failed instanceof BadCredentialsException) {
			out.write(mapper.writeValueAsString(Response.ofFailed(ErrorCode.AUTHEN_FAIL)));
		} else if (failed instanceof LockedException) {
			out.write(mapper.writeValueAsString(Response.ofFailed(ErrorCode.ACCOUNT_LOCKED)));
		}
		out.flush();
	}
}
