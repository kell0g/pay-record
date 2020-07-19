package com.toyproject.payrecord.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toyproject.payrecord.domain.employee.domain.Employee;
import com.toyproject.payrecord.repository.EmployeeRepository;
import com.toyproject.payrecord.service.impl.AccountContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// username => email
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(email);

		if (employee == null) {
			throw new UsernameNotFoundException("user(email) name doesn't exist");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		employee.getRoles().forEach(role -> roles.add(role));

		// roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		// admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		AccountContext accountContext = new AccountContext(employee, roles);

		log.info(employee.getEmail());
		log.info(employee.getPassword());

		return accountContext;
	}
}

