package com.java.gamedev.GameDev.service.desarrollador;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.DesarrolladorDTO;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DesarrolladorService {
    List<DesarrolladorResponseDTO> getAllDesarrolladores(String nombre);
    Optional<DesarrolladorResponseDTO> getDesarrolladorById(UUID uuid);
    Desarrollador crearDesarrollador(DesarrolladorResponseDTO desarrolladorResponseDTO);

    boolean borrarDesarrollador(UUID uuid);
    Desarrollador actualizarDesarrollador(UUID uuid, DesarrolladorResponseDTO desarrolladorResponseDTO);
}
