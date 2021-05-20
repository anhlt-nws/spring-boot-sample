package vn.nws.sample.web.api.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AppUserDetailsService.
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

	/** The app repo. */
//	@Autowired
//	private AdminRepository adminRepo;
	
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
		
//		SoAdmin admin = this.adminRepo.findByEmailIgnoreCase(username.trim()).orElse(null);
//        if (admin == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        SoUserDetails userDetails = new SoUserDetails(
//				admin.getEmail(),
//				admin.getPassword(), 
//				admin.getStatus() != AdminStatus.INACTIVE,
//				true,
//				true,
//				admin.getStatus() != AdminStatus.LOCKED,
//				Collections.singletonList(new SimpleGrantedAuthority(admin.getRole().name())));
//        userDetails.setUserId(admin.getId());
//        userDetails.setFullname(admin.getFullname());
//        
//        userDetails.setOtpRequired(isOtpRequired(admin.getLastLoginTime()));
//
//		return userDetails;
		return null;
	}

}
