package com.toyproject.payrecord.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.payrecord.domain.employee.EmployeeDto;
import com.toyproject.payrecord.domain.employee.Role;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import com.toyproject.payrecord.service.EmployeeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "SignUp")
public class UserController {

	// RepositoryService
	@Autowired
	private EmployeeService userService;

	// password Encryption
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public ResponseEntity<String> createUser(@RequestBody EmployeeDto employeeDto) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		
		// List<Role> roles = new ArrayList<>(Arrays.asList(Role.ROLE_EMPLOYEE));
		List<Role> roles = new ArrayList<>(Arrays.asList(Role.ROLE_EMPLOYEE, Role.ROLE_MANAGER));
		employee.setRoles(roles);

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));

		try {
			userService.createUser(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("fail signup", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("successful signup", HttpStatus.OK);
	}
}