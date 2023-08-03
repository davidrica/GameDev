package com.java.gamedev.GameDev.service.csv.juego;

import com.java.gamedev.GameDev.model.csv.juego.JuegoCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface JuegoCsvService {
    List<JuegoCsvRecord> importarCsv(File file) throws FileNotFoundException;
}
