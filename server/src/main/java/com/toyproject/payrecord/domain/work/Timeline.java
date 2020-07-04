package com.toyproject.payrecord.domain.work;

import com.toyproject.payrecord.domain.BaseEntity;
import com.toyproject.payrecord.global.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "timeline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timeline extends BaseEntity {
    private static final String NULL_NOT_ALLOW = "공백을 입력할 수 없습니다.";

    @Column(name = "event", length = 40, nullable = false)
    private String event;

    public Timeline(String event) {
        if (StringUtils.isBlank(event)) {
            throw new IllegalArgumentException(NULL_NOT_ALLOW);
        }
        this.event = event;
    }
}
