package com.toyproject.payrecord.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepository empRepository;

    public Employee singUp(String name, String email, String password) {
        Employee employee = new Employee(name, email, password, null);

        return empRepository.save(employee);
    }
}
