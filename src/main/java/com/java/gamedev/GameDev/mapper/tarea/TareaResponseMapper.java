package com.java.gamedev.GameDev.mapper.tarea;

import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.model.dto.TareaResponseDTO;

public interface TareaResponseMapper {
    TareaResponseDTO tareaToTareaResponseDTO(Tarea tarea);

    Tarea tareaResponseDTOToTarea(TareaResponseDTO tareaResponseDTO);


}
