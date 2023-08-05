package com.java.gamedev.GameDev.service.util.impl;

import com.java.gamedev.GameDev.service.util.UtilService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public LocalDate createFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate fecha = LocalDate.now();
        if (!date.isBlank()){
            fecha = LocalDate.parse(date, formatter);
        }
        return fecha;
    }

    public LocalDate getLocalDateTime(String date) {
        LocalDate f = LocalDate.now();
        if (!date.isBlank()){
            String[] parts = date.split("/");

            return LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
        }
        return null;

    }
    public String setLocalDateTime(LocalDate localDateTime){
        StringBuffer stringBuffer = new StringBuffer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        return formatter.format(localDateTime);
    }

}
