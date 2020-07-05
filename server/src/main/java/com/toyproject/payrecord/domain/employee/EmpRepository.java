package com.toyproject.payrecord.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);
}
