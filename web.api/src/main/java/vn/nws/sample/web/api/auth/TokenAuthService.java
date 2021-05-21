package vn.nws.sample.web.api.auth;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.nws.sample.service.response.Response;

@Service
public class TokenAuthService {
	public static final String AUTH_HEADER_NAME = "X-Access-Token";

	@Autowired
	private JwtTokenHandler jwtTokenHandler;

	@Autowired
	private ObjectMapper mapper;

	public void addJwtTokenToHeader(HttpServletResponse response, UserAuthentication authentication) throws Exception {
		final SoUserDetails user = (SoUserDetails) authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, jwtTokenHandler.createTokenForUser(user));
	}

	public void addJwtTokenToResponse(HttpServletResponse response, UserAuthentication authentication)
			throws Exception {
		final SoUserDetails user = (SoUserDetails) authentication.getDetails();
		AuthTokenResponse authTokenResponse = new AuthTokenResponse();
		authTokenResponse.setAccessToken(jwtTokenHandler.createTokenForUser(user));

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		out.print(mapper.writeValueAsString(Response.ofSucceeded(authTokenResponse)));
		out.flush();
	}

	public Authentication generateAuthenticationFromRequest(HttpServletRequest request) throws Exception {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token == null || token.isEmpty())
			return null;
		return this.jwtTokenHandler.parseUserFromToken(token).map(UserAuthentication::new).orElse(null);
	}
}
