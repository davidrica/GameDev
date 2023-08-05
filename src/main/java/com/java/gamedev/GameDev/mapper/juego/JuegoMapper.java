package com.java.gamedev.GameDev.mapper.juego;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;

public interface JuegoMapper {
    Juego juegoDtoToJuego(JuegoDTO juegoDTO);

    JuegoDTO juegoToJuegoDto(Juego juego);
}
