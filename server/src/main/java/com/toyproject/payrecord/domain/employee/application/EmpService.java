package com.toyproject.payrecord.domain.employee.application;

import com.toyproject.payrecord.domain.employee.domain.EmpRepository;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepository empRepository;

    public Employee singUp(String name, String email, String password) {
        Employee employee = new Employee(name, email, password, null);

        return empRepository.save(employee);
    }
}
