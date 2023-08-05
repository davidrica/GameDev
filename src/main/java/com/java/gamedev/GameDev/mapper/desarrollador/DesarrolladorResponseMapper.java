package com.java.gamedev.GameDev.mapper.desarrollador;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import org.springframework.stereotype.Component;


public interface DesarrolladorResponseMapper {
    DesarrolladorResponseDTO desarrolladorToDesarrolladorResponseDTO(Desarrollador desarrollador);
    Desarrollador desarrolladorResponseDtoToDesarrollador(DesarrolladorResponseDTO desarrolladorResponseDTO);
}
