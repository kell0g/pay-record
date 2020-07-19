package com.toyproject.payrecord.controller;

import javax.servlet.http.HttpServletRequest;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.payrecord.domain.employee.EmployeeDto;
import com.toyproject.payrecord.service.EmployeeService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Test")
public class TestController {

	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private EmployeeService userService;

	// password Encryption
	@Autowired

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String jwtTest() {
		return "success";
	}

	@GetMapping(value = "/me")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public EmployeeDto whoami(HttpServletRequest req) {
		return modelMapper.map(userService.whoami(req), EmployeeDto.class);
	}
	
}
