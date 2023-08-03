package com.java.gamedev.GameDev.service.csv.desarrollador;

import com.java.gamedev.GameDev.model.csv.desarrollador.DesarrolladorCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface DesarrolladorCsvService {
    List<DesarrolladorCsvRecord> importarCsv(File file) throws FileNotFoundException;
}
