package com.java.gamedev.GameDev.mapper.desarrollador.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.mapper.desarrollador.DesarrolladorResponseMapper;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class DesarrolladorResponseMapperImpl implements DesarrolladorResponseMapper {

    @Override
    public DesarrolladorResponseDTO desarrolladorToDesarrolladorResponseDTO(Desarrollador desarrollador) {
        return DesarrolladorResponseDTO.builder()
                .rol(desarrollador.getRol())
                .email(desarrollador.getEmail())
                .nombre(desarrollador.getNombre())
                .uuid(desarrollador.getUuid().toString())
                .build();
    }

    @Override
    public Desarrollador desarrolladorResponseDtoToDesarrollador(DesarrolladorResponseDTO desarrolladorResponseDTO) {

        return Desarrollador.builder()
                .rol(desarrolladorResponseDTO.getRol())
                .email(desarrolladorResponseDTO.getEmail())
                .nombre(desarrolladorResponseDTO.getNombre())
                .build();
    }
}
