package com.java.gamedev.GameDev.mapper.tarea;

import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.model.dto.TareaDTO;


public interface TareaMapper {
    TareaDTO tareaToTareaDTO(Tarea tarea);

    Tarea tareaDTOToTarea(TareaDTO tareaDTO);

}
