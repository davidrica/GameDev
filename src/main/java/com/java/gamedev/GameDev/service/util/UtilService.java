package com.java.gamedev.GameDev.service.util;

import java.time.LocalDate;

public interface UtilService {
    LocalDate createFromString(String date);
    LocalDate getLocalDateTime(String date);
    String setLocalDateTime(LocalDate localDateTime);
}
