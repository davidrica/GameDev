package com.java.gamedev.GameDev.model.csv.desarrollador;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DesarrolladorCsvRecord {

    @CsvBindByName(column = "nombre")
    private String nombre;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "rol")
    private String rol;



}
