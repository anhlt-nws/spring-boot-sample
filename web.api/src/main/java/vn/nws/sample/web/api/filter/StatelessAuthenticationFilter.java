package vn.nws.sample.web.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import vn.nws.sample.web.api.auth.TokenAuthService;

/**
 * The Class StatelessAuthenticationFilter.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);
	
	/** The token authentication service. */
	private final TokenAuthService tokenAuthenticationService;

	/**
	 * Instantiates a new stateless authentication filter.
	 *
	 * @param tokenAuthenticationService the token authentication service
	 */
	public StatelessAuthenticationFilter(TokenAuthService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	/**
	 * Do filter.
	 *
	 * @param req the req
	 * @param res the res
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			Authentication authentication = tokenAuthenticationService
					.generateAuthenticationFromRequest((HttpServletRequest) req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
			SecurityContextHolder.clearContext();
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			logger.info("Cannot get Authentication: ", e);
			((HttpServletResponse) res).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	
}