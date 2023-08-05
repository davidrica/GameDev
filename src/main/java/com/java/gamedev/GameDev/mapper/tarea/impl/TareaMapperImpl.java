package com.java.gamedev.GameDev.mapper.tarea.impl;

import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.mapper.tarea.TareaMapper;
import com.java.gamedev.GameDev.model.dto.TareaDTO;
import com.java.gamedev.GameDev.service.util.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class TareaMapperImpl implements TareaMapper {
    UtilService utilService;
    @Autowired
    public TareaMapperImpl(UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public TareaDTO tareaToTareaDTO(Tarea tarea) {
        return TareaDTO.builder()
                .idjuego(tarea.getJuego().getUuid().toString())
                .descripcion(tarea.getDescripcion())
                .fechaLimite(utilService.setLocalDateTime (tarea.getFechaLimite()) )
                .desarrollador(tarea.getDesarrollador())
                .juego(tarea.getJuego())
                .build();
    }

    @Override
    public Tarea tareaDTOToTarea(TareaDTO tareaDTO) {
        return Tarea.builder()
                .uuid(UUID.randomUUID())
                .descripcion(tareaDTO.getDescripcion())
                .fechaLimite(utilService.createFromString(tareaDTO.getFechaLimite()))
                .desarrollador(tareaDTO.getDesarrollador())
                .build();
    }
}
