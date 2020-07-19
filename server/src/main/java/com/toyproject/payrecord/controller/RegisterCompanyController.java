package com.toyproject.payrecord.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.payrecord.domain.company.CompanyDto;
import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import com.toyproject.payrecord.service.EmployeeService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "CompanyRegister")
public class RegisterCompanyController {
	@Autowired
	private EmployeeService userService;

	/*
	 * 1. 회원가입 후, /api/v1/register
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<String> registerCompany(HttpServletRequest req, @RequestBody CompanyDto companyDto) {
		ModelMapper modelMapper = new ModelMapper();
		ObjectMapper objectMapper = new ObjectMapper();
		log.info(companyDto.getName());
		// Receive CompnayDto and mapper to Company
		System.err.println(companyDto.getAddress().getCity());
		
		

		// using jwt, find employee information
		Employee employee = modelMapper.map(userService.whoami(req), Employee.class);
		
		// nested json... how to handle.. T__T
		Company company = modelMapper.map(companyDto, Company.class);
		//company.setAddress(companyDto.getAddress());
		log.info(company.getAddress().getCity());
		
		
		userService.registerCompany(employee, company);

		return new ResponseEntity<>("G__G?", HttpStatus.BAD_REQUEST);
	}

}

//@GetMapping(value = "/me")
//@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//public EmployeeDto whoami(HttpServletRequest req) {
//	return modelMapper.map(userService.whoami(req), EmployeeDto.class);
//}
//@RequestMapping(method = RequestMethod.POST, value = "/signup")
//public ResponseEntity<String> createUser(@RequestBody EmployeeDto employeeDto) throws Exception {
//	ModelMapper modelMapper = new ModelMapper();
//	Employee employee = modelMapper.map(employeeDto, Employee.class);
//	
//	// List<Role> roles = new ArrayList<>(Arrays.asList(Role.ROLE_EMPLOYEE));
//	List<Role> roles = new ArrayList<>(Arrays.asList(Role.ROLE_EMPLOYEE, Role.ROLE_MANAGER));
//	employee.setRoles(roles);
//
//	employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//
//	try {
//		userService.createUser(employee);
//	} catch (Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>("fail signup", HttpStatus.BAD_REQUEST);
//	}
//	return new ResponseEntity<>("successful signup", HttpStatus.OK);
//}