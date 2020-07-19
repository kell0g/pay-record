package com.toyproject.payrecord.domain.company;

import javax.persistence.Embedded;

import lombok.Data;

@Data
public class CompanyDto {
	
	private String CompanyName;
	
	@Embedded
	private Address address;
}
