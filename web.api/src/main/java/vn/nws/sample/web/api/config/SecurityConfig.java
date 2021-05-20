package vn.nws.sample.web.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import vn.nws.sample.web.api.auth.AppUserDetailsService;
import vn.nws.sample.web.api.auth.TokenAuthService;
import vn.nws.sample.web.api.filter.StatelessAuthenticationFilter;
import vn.nws.sample.web.api.filter.StatelessLoginFilter;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** The Constant DEV_ENV. */
	private final static String DEV_ENV = "dev";
	
	/** The homepage. */
	@Value("${homepage:}")
	private String homepage;
	
	/** The active profile. */
	@Value("${spring.profiles.active:prod}")
	private String activeProfile;

	/** The token authentication service. */
	@Autowired
	private TokenAuthService tokenAuthenticationService;

	/** The app user details service. */
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure.
	 *
	 * @param web the web
	 * @throws Exception the exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
	}
	
	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 * @throws Exception the exception
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() throws Exception {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(DEV_ENV.equals(this.activeProfile) ? "*" : homepage));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(false);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http	
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/webapi/auth/**").permitAll()
			.antMatchers("/webapi/**").authenticated()
			.antMatchers("/**").permitAll();
		
		http.addFilterBefore(
				new StatelessLoginFilter("/webapi/auth/login",
						tokenAuthenticationService,
						appUserDetailsService,
						authenticationManager()),
				UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(
				new StatelessAuthenticationFilter(tokenAuthenticationService),
				UsernamePasswordAuthenticationFilter.class);
		http.cors();
		http.csrf().disable();
	}
}
