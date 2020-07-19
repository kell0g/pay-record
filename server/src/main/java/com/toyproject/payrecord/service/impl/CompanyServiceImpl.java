package com.toyproject.payrecord.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.repository.CompanyRepository;
import com.toyproject.payrecord.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}
	
	
//
//	
//	// Sign up
//	@Transactional
//	@Override
//	public void createUser(Employee employee) {
//		userRepository.save(employee);
//	}
//
//	
//	// Sign in with JWT
//	public String signin(String username, String password) {
//		try {
//			log.info(password);
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//			return jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
//		} catch (AuthenticationException e) {
//			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	
//	// Authorization using JWT
//	public Employee whoami(HttpServletRequest req) {
//		return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
//	}
//
//
//	// Register company
//	@Override
//	public void registerCompany(Employee employee, Company company) {
//		employee.setCompany(company);
//		userRepository.save(employee);
//	}
}
