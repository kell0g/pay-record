package com.toyproject.payrecord.domain.employee.domain;

import com.toyproject.payrecord.domain.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);
}
