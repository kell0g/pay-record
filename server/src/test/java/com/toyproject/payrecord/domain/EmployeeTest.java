package com.toyproject.payrecord.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private String email;
    private String password;
    private String fcmToken;

    @BeforeEach
    public void setUp() {
        email = "aaa@aaa.com";
        password = "aaa";
        fcmToken = "aaa";
    }

    @DisplayName(value = "이름에 빈 문자열 또는 null 값을 입력할 경우, IllegalArgumentException 이 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void testEmptyOrNull(final String inputText) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Employee(inputText, email, password, fcmToken));
    }

    @DisplayName(value = "이름에 영문, 한글이 아닌 값을 입력할 경우, IllegalArgumentException 이 발생")
    @ParameterizedTest
    @ValueSource(strings = {"**", "999", "&&&"})
    void testNotName (final String inputText) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Employee(inputText, email, password, fcmToken));
    }
}
