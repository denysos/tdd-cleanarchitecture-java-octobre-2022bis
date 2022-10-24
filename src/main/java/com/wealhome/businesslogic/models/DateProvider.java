package com.wealhome.businesslogic.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateProvider {

    LocalDateTime timeNow();

    LocalDate dateNow();

}
