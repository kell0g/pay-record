package com.toyproject.payrecord.service;

import javax.servlet.http.HttpServletRequest;

import com.toyproject.payrecord.domain.company.Company;
import com.toyproject.payrecord.domain.employee.Employee;

public interface EmployeeService {
	
	// Sign up
	void createUser(Employee employee);
	
	// Sign in with JWT
	String signin(String username, String password);
	Employee whoami(HttpServletRequest req);
	
	// Register company
	void registerCompany(Employee employee, Company company);
}
