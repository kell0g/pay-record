package com.toyproject.payrecord.domain.employee;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmployeeDto {
	
	@NotNull
	private String email;

	@NotNull
	private String password;
	// private String fcmToken;
	// private Company company;
}
