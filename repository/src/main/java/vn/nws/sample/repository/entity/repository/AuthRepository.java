package vn.nws.sample.repository.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.nws.sample.repository.entity.Auth;
import vn.nws.sample.repository.entity.enumerate.AuthStatus;

/**
 * The Interface AuthRepository.
 */
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
	
	/**
	 * Find by username and status.
	 *
	 * @param username the username
	 * @param status the status
	 * @return the optional
	 */
	Optional<Auth> findByUsernameAndStatus(String username, AuthStatus status);
}
