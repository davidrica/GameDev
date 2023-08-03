package com.java.gamedev.GameDev.model.csv.juego;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class JuegoCsvRecord {
    @CsvBindByName(column="titulo")
    private String titulo;

    @CsvBindByName(column ="descripcion" )
    private String descripcion;

    @CsvBindByName(column = "fechaLanzamiento")
    private String fechaLanzamiento;

}
