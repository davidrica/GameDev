package com.java.gamedev.GameDev.service.csv.desarrollador.impl;

import com.java.gamedev.GameDev.model.csv.desarrollador.DesarrolladorCsvRecord;
import com.java.gamedev.GameDev.service.csv.desarrollador.DesarrolladorCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class DesarrolladorCsvServiceImpl implements DesarrolladorCsvService {

    @Override
    public List<DesarrolladorCsvRecord> importarCsv(File file) throws FileNotFoundException {
        return new CsvToBeanBuilder<DesarrolladorCsvRecord>(new FileReader(file) )
                .withType(DesarrolladorCsvRecord.class)
                .build()
                .parse();
    }
}
