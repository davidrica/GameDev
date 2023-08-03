package com.java.gamedev.GameDev.service.csv.tarea.impl;

import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.model.csv.juego.JuegoCsvRecord;
import com.java.gamedev.GameDev.model.csv.tarea.TareaCsvRecord;
import com.java.gamedev.GameDev.service.csv.tarea.TareaCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@Slf4j
public class TareaCsvServiceImpl implements TareaCsvService {
    @Override
    public List<TareaCsvRecord> importarCsv(File file) throws FileNotFoundException {
        return new CsvToBeanBuilder<TareaCsvRecord>(new FileReader(file) )
                .withType(TareaCsvRecord.class)
                .build()
                .parse();
    }
}
