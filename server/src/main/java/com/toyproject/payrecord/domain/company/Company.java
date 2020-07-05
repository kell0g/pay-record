package com.toyproject.payrecord.domain.company;

import com.toyproject.payrecord.domain.BaseEntity;
import com.toyproject.payrecord.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "company", fetch = LAZY)
    private Employee employee;
}
