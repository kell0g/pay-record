package com.toyproject.payrecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.payrecord.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Login")
public class LoginController {

	@Autowired
	private EmployeeService userService;

	// password Encryption
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public String createUser(@ApiParam("Username") @RequestParam String username, //
			@ApiParam("Password") @RequestParam String password) throws Exception {

		log.info("Signin Controller");

		return userService.signin(username, password);
	}
}
