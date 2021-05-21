package vn.nws.sample.repository.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import vn.nws.sample.repository.entity.enumerate.AuthStatus;


/**
 * The persistent class for the auth database table.
 * 
 */
@Entity
@Getter
@Setter
@FieldNameConstants
public class Auth extends BaseEntity {

	@Column(name="last_login_time")
	private Timestamp lastLoginTime;

	private String password;

	private String role;

	@Enumerated(EnumType.STRING)
	private AuthStatus status;

	private String username;

	public Auth() {
	}
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private User user;
}