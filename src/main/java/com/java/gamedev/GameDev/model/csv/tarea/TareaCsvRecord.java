package com.java.gamedev.GameDev.model.csv.tarea;

import com.java.gamedev.GameDev.enumeration.EstadoEnum;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TareaCsvRecord {
    @CsvBindByName(column = "descripcion")
    private String descripcion;
    @CsvBindByName (column = "fehcaLimite")
    private String fehcaLimite;
    @CsvBindByName (column ="estado" )
    private String estado;
}
