package com.java.gamedev.GameDev.service.csv.tarea;

import com.java.gamedev.GameDev.model.csv.juego.JuegoCsvRecord;
import com.java.gamedev.GameDev.model.csv.tarea.TareaCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface TareaCsvService {
    List<TareaCsvRecord> importarCsv(File file) throws FileNotFoundException;
}
