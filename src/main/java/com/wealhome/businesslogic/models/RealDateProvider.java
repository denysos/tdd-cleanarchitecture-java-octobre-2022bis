package com.wealhome.businesslogic.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RealDateProvider implements DateProvider {

    @Override
    public LocalDateTime timeNow() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate dateNow() {
        return timeNow().toLocalDate();
    }
}
