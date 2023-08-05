package com.java.gamedev.GameDev.mapper.juego.impl;

import com.java.gamedev.GameDev.domain.Juego;

import com.java.gamedev.GameDev.mapper.juego.JuegoMapper;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import com.java.gamedev.GameDev.service.util.UtilService;
import com.java.gamedev.GameDev.service.util.impl.UtilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class JuegoMapperImpl implements JuegoMapper {
    private final UtilService utilService;
    @Autowired
    public JuegoMapperImpl(UtilService utilService) {
        this.utilService = utilService;
    }



    @Override
    public Juego juegoDtoToJuego(JuegoDTO juegoDTO) {

        return Juego.builder()
                .uuid(UUID.randomUUID())
                .titulo(juegoDTO.getTitulo())
                .descripcion(juegoDTO.getDescripcion())
                .fechaLanzamiento(utilService.createFromString(juegoDTO.getFechaLanzamiento()) )
                .build();
    }

    @Override
    public JuegoDTO juegoToJuegoDto(Juego juego) {
        return JuegoDTO.builder()
                .UUII(juego.getUuid().toString())
                .titulo(juego.getTitulo())
                .descripcion(juego.getDescripcion())
                .fechaLanzamiento(utilService.setLocalDateTime(juego.getFechaLanzamiento())  )
                .build();
    };

}
