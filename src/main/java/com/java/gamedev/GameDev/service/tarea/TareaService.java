package com.java.gamedev.GameDev.service.tarea;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.model.dto.TareaDTO;
import com.java.gamedev.GameDev.model.dto.TareaResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TareaService {
    List<TareaResponseDTO> getAllTareas(String idTarea,String idDesarrollador,String idJuego,String estado,String plazo);

    Optional<TareaResponseDTO>   actualizarEstado(UUID uuid, String estado);

    Tarea crearTarea(TareaDTO tareaDTO);
}
