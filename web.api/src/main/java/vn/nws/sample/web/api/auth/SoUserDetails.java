package vn.nws.sample.web.api.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoUserDetails extends User {

	/**
	 * Instantiates a new so user details.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 * @param accountNonExpired the account non expired
	 * @param credentialsNonExpired the credentials non expired
	 * @param accountNonLocked the account non locked
	 * @param authorities the authorities
	 */
	public SoUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9191266872740364386L;

	/** The user id. */
	private Long userId;
	
	/** The fullname. */
	private String fullname;
}
