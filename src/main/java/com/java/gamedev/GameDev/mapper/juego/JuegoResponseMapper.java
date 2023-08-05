package com.java.gamedev.GameDev.mapper.juego;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;

public interface JuegoResponseMapper {
    JuegoResponseDTO juegoToJuegosReponseDTO(Juego juego);
    Juego juegoResponseDTOToJuego(JuegoResponseDTO juegoResponseDTO);
}
