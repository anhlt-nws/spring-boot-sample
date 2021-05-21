package vn.nws.sample.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import vn.nws.sample.repository.entity.enumerate.UserStatus;


/**
 * The persistent class for the user database table.
 * 
 */
@Getter
@Setter
@FieldNameConstants
@Entity(name = "users")
public class User extends BaseEntity {

	@Column(name="auth_id")
	private Long authId;

	private String fullname;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	public User() {
	}

}