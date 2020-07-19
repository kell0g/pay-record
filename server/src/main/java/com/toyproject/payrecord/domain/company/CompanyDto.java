package com.toyproject.payrecord.domain.company;

import javax.persistence.Embedded;

import com.toyproject.payrecord.domain.company.domain.Address;

import lombok.Data;

@Data
public class CompanyDto {
	
	private String name;
	
	@Embedded
	private Address address;
}
