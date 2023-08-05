package com.java.gamedev.GameDev.mapper.juego.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.mapper.desarrollador.DesarrolladorResponseMapper;
import com.java.gamedev.GameDev.mapper.juego.JuegoResponseMapper;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class JuegoResponseMapperImpl implements JuegoResponseMapper {

    DesarrolladorResponseMapper desarrolladorResponseMapper;
    @Autowired
    public JuegoResponseMapperImpl(DesarrolladorResponseMapper desarrolladorResponseMapper) {
        this.desarrolladorResponseMapper = desarrolladorResponseMapper;
    }

    @Override
    public JuegoResponseDTO juegoToJuegosReponseDTO(Juego juego) {
        JuegoResponseDTO juegoResponseDTO =JuegoResponseDTO.builder()
                .idJuego(juego.getUuid().toString())
                .descripcion(juego.getDescripcion())
                .fechalanzamiento(juego.getFechaLanzamiento())
                .titulo(juego.getTitulo())
                .build();



        juegoResponseDTO.setDesarrolladorResponseDTOList(getDesarrolladoresResponseDTOS(juego.getDesarrolladores()));

        return juegoResponseDTO;
    }

    @Override
    public Juego juegoResponseDTOToJuego(JuegoResponseDTO juegoResponseDTO) {

        return Juego.builder()
                .uuid(UUID.fromString(juegoResponseDTO.getIdJuego()) )
                .titulo(juegoResponseDTO.getTitulo())
                .descripcion(juegoResponseDTO.getDescripcion())
                .fechaLanzamiento(juegoResponseDTO.getFechalanzamiento())

                .build();
    }

    private List<DesarrolladorResponseDTO> getDesarrolladoresResponseDTOS(List<Desarrollador> desarrolladors){
        List<DesarrolladorResponseDTO> desarrolladorResponseDTOS = new ArrayList<>();
        for (Desarrollador desarrollador:desarrolladors) {

            desarrolladorResponseDTOS.add(desarrolladorResponseMapper.desarrolladorToDesarrolladorResponseDTO(desarrollador));
        }
        return desarrolladorResponseDTOS;
    }

}
