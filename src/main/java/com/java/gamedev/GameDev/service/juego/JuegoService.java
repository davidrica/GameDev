package com.java.gamedev.GameDev.service.juego;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JuegoService {

    //List<JuegoDTO> getAllJuegos();
    List<JuegoDTO> getAllJuegos( String titulo,String finalizado);

    Optional <JuegoResponseDTO> getJuegoById(UUID uuid);

    Juego crearJuego(JuegoDTO juegoDTO);

    boolean borrarJuego(UUID uuid);


    Juego actualizarJuego(UUID uuid, JuegoDTO juegoDTO);

    Optional <JuegoResponseDTO> asignarDesarrollador(UUID uuidJuego, UUID uuidDesarrollador);

}
