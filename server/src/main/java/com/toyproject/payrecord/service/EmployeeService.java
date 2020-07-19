package com.toyproject.payrecord.service;

import javax.servlet.http.HttpServletRequest;

import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.domain.employee.domain.Employee;

public interface EmployeeService {
	
	// Sign up
	void createUser(Employee employee);
	
	// Sign in with JWT
	String signin(String username, String password);
	Employee whoami(HttpServletRequest req);
	
	// Register company
	void registerCompany(Employee employee, Company company);
}
