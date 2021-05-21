package vn.nws.sample.repository.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.nws.sample.repository.entity.User;
import vn.nws.sample.repository.entity.enumerate.UserStatus;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find by auth id.
	 *
	 * @param authId the auth id
	 * @return the optional
	 */
	Optional<User> findByAuthId(Long authId);
	
	/**
	 * Find by id and status.
	 *
	 * @param id the id
	 * @param status the status
	 * @return the optional
	 */
	Optional<User> findByIdAndStatus(Long id, UserStatus status);
}
