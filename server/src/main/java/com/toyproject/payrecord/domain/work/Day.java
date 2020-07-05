package com.toyproject.payrecord.domain.work;


import com.toyproject.payrecord.domain.BaseEntity;
import com.toyproject.payrecord.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Entity
@Table(name = "day")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Day {

    @EmbeddedId
    private DayId dayId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    @MapsId("employee_id")
    private Employee employee;

    private int startTime;
    private int endTime;
    private boolean isLate;

    private int workStartTime;
    private int workEndTime;

    private int planTime;
    private int remainTime;

    private int timePay;

    private String month;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Timeline> timelines = new ArrayList<>();

}
