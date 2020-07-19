package com.toyproject.payrecord.service.impl;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.toyproject.payrecord.domain.employee.Employee;

import lombok.Data;

@Data
public class AccountContext extends User {
	private Employee employee;

	public AccountContext(Employee employee, List<GrantedAuthority> roles) {
		// super(account.getUsername(), account.getPassword(), roles);
		super(employee.getEmail(), employee.getPassword(), roles);
		this.employee = employee;
	}
}