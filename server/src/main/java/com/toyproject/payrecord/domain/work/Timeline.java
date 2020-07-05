package com.toyproject.payrecord.domain.work;

import com.toyproject.payrecord.domain.BaseEntity;
import com.toyproject.payrecord.global.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "timeline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timeline extends BaseEntity {
    private static final String NULL_NOT_ALLOW = "공백을 입력할 수 없습니다.";

    @Id
    @Column(name = "timeline_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event", length = 40, nullable = false)
    private String event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "employee_id"),
            @JoinColumn(name = "date")
    })
    private Day day;

    public Timeline(String event) {
        if (StringUtils.isBlank(event)) {
            throw new IllegalArgumentException(NULL_NOT_ALLOW);
        }
        this.event = event;
    }
}
