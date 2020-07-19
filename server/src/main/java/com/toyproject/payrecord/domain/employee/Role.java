package com.toyproject.payrecord.domain.employee;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	  ROLE_MANAGER, ROLE_EMPLOYEE;

	  public String getAuthority() {
	    return name();
	  }

	}
