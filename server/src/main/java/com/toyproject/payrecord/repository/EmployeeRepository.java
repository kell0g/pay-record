package com.toyproject.payrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.payrecord.domain.employee.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);
}
