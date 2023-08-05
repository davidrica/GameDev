package com.java.gamedev.GameDev.mapper.tarea.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.enumeration.EstadoEnum;
import com.java.gamedev.GameDev.mapper.desarrollador.DesarrolladorResponseMapper;
import com.java.gamedev.GameDev.mapper.juego.JuegoMapper;
import com.java.gamedev.GameDev.mapper.juego.JuegoResponseMapper;
import com.java.gamedev.GameDev.mapper.tarea.TareaResponseMapper;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.model.dto.TareaResponseDTO;
import com.java.gamedev.GameDev.service.util.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class TareaResponseMapperImpl implements TareaResponseMapper {
    UtilService utilService;
    DesarrolladorResponseMapper desarrolladorResponseMapper;
    JuegoResponseMapper juegoResponseMapper;

    @Autowired
    public TareaResponseMapperImpl(UtilService utilService,DesarrolladorResponseMapper desarrolladorResponseMapper,JuegoResponseMapper juegoResponseMapper) {

        this.utilService = utilService;
        this.desarrolladorResponseMapper = desarrolladorResponseMapper;
        this.juegoResponseMapper = juegoResponseMapper;

    }

    @Override
    public TareaResponseDTO tareaToTareaResponseDTO(Tarea tarea) {
        return TareaResponseDTO.builder()
                .idtarea(tarea.getUuid().toString())
                .descripcion(tarea.getDescripcion())
                .fechaLimite(utilService.setLocalDateTime(tarea.getFechaLimite()) )
                .iddesarrollador(tarea.getDesarrollador().getUuid().toString())
                .desarrolladorResponseDTO(
                        desarrolladorResponseMapper.desarrolladorToDesarrolladorResponseDTO(tarea.getDesarrollador())  )
                .idjuego(tarea.getJuego().getUuid().toString())
                .juegoResponseDTO(juegoResponseMapper.juegoToJuegosReponseDTO(tarea.getJuego()) )
                .estado(tarea.getEstado().toString())
                .build();
    }

    @Override
    public Tarea tareaResponseDTOToTarea(TareaResponseDTO tareaResponseDTO) {


        return Tarea.builder()
                .uuid(UUID.randomUUID())
                .descripcion(tareaResponseDTO.getDescripcion())
                .estado(EstadoEnum.valueOf("PENDIENTE"))
                .fechaLimite(utilService.createFromString(tareaResponseDTO.getFechaLimite()))
                .desarrollador(
                        desarrolladorResponseMapper.desarrolladorResponseDtoToDesarrollador(tareaResponseDTO.getDesarrolladorResponseDTO()))
                .juego(
                        juegoResponseMapper.juegoResponseDTOToJuego(tareaResponseDTO.getJuegoResponseDTO()))
                .build();
    }
}
