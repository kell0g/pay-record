package com.toyproject.payrecord.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import com.toyproject.payrecord.exception.CustomException;
import com.toyproject.payrecord.repository.EmployeeRepository;
import com.toyproject.payrecord.security.provider.JwtTokenProvider;
import com.toyproject.payrecord.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	// Sign up
	@Transactional
	@Override
	public void createUser(Employee employee) {
		userRepository.save(employee);
	}

	
	// Sign in with JWT
	public String signin(String username, String password) {
		try {
			log.info(password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	
	// Authorization using JWT
	public Employee whoami(HttpServletRequest req) {
		return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}


	// Register company
	@Override
	public void registerCompany(Employee employee, Company company) {
		employee.setCompany(company);
		userRepository.save(employee);
	}
}
