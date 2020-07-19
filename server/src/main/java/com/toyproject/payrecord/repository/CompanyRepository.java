package com.toyproject.payrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.payrecord.domain.company.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	Company findByName(String name);
}



