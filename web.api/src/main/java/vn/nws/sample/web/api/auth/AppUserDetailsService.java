package vn.nws.sample.web.api.auth;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.nws.sample.repository.entity.Auth;
import vn.nws.sample.repository.entity.User;
import vn.nws.sample.repository.entity.enumerate.AuthStatus;
import vn.nws.sample.repository.entity.repository.AuthRepository;
import vn.nws.sample.repository.entity.repository.UserRepository;

/**
 * The Class AppUserDetailsService.
 */
@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

	/** The auth repo. */
	private final AuthRepository authRepo;

	/** The user repo. */
	private final UserRepository userRepo;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Auth admin = this.authRepo.findByUsernameAndStatus(username, AuthStatus.ACTIVE).orElse(null);
		if (admin == null) {
			throw new UsernameNotFoundException("User not found");
		}

		SoUserDetails userDetails = new SoUserDetails(admin.getUsername(), 
				admin.getPassword(),
				admin.getStatus() != AuthStatus.INACTIVE, 
				true, 
				true, 
				admin.getStatus() != AuthStatus.LOCKED,
				Collections.singletonList(new SimpleGrantedAuthority(admin.getRole())));

		User user = this.userRepo.findByAuthId(admin.getId()).orElse(null);

		if (user != null) {
			userDetails.setUserId(user.getId());
			userDetails.setFullname(user.getFullname());
		}

		return userDetails;
	}

}
