package com.wealhome.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DeterministicDateProvider implements DateProvider {

    private LocalDateTime timeNow;

    @Override
    public LocalDateTime timeNow() {
        return this.timeNow;
    }

    @Override
    public LocalDate dateNow() {
        return this.timeNow.toLocalDate();
    }

    public void setTime(LocalDateTime time) {
        timeNow = time;
    }
}
