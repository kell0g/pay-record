package com.toyproject.payrecord.domain.employee.domain;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.domain.employee.Role;
import com.toyproject.payrecord.global.utils.StringUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "이메일", required = true)
	@Column(name = "email", length = 30, nullable = false, unique = true)
	private String email;

	@ApiModelProperty(value = "이름", required = false)
	@Column(name = "name", length = 30, nullable = true)
	private String name;

	@ApiModelProperty(hidden = true)
	@Column(name = "password", length = 255, nullable = true)
	private String password;

	@ApiModelProperty(value = "fcm토큰", required = false)
	@Column(name = "fcm_token", length = 255, nullable = true)
	private String fcmToken;

	// CASCADE 추가
	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private Company company;

	// ROLE JPA 값타입으로 추가
	@ApiModelProperty(value = "ROLE", required = false)
	@ElementCollection(fetch = FetchType.EAGER)
	List<Role> roles;

	public Employee(String name, String email, String password, String fcmToken) {
		validate(name, email, password);
		this.name = name;
		this.email = email;
		this.password = password;
		this.fcmToken = fcmToken == null ? null : fcmToken;
	}

	private void validate(String name, String email, String password) {
		StringUtils.validateName(name);
		StringUtils.validateEmail(email);
		StringUtils.validatePassword(password);
	}

	public Employee(String email, String password) {
		this(email, password, null);
	}

	public Employee(String email, String password, String fcmToken) {
		validate(email, password);
		this.email = email;
		this.password = password;
		this.fcmToken = fcmToken == null ? null : fcmToken;
	}

	private void validate(String email, String password) {
		StringUtils.validateEmail(email);
		StringUtils.validatePassword(password);
	}

}
