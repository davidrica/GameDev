package com.java.gamedev.GameDev.service.csv.juego.impl;


import com.java.gamedev.GameDev.model.csv.juego.JuegoCsvRecord;
import com.java.gamedev.GameDev.service.csv.juego.JuegoCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Slf4j
@Service
public class JuegoCsvServiceImpl implements JuegoCsvService {

    @Override
    public List<JuegoCsvRecord> importarCsv(File file) throws FileNotFoundException {
        return new CsvToBeanBuilder<JuegoCsvRecord>(new FileReader(file) )
                .withType(JuegoCsvRecord.class)
                .build()
                .parse();
    }
}
