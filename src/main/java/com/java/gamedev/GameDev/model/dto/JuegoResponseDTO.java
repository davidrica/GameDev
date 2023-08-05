package com.java.gamedev.GameDev.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JuegoResponseDTO {
    private String idJuego;
    private String titulo;
    private String descripcion;
    private LocalDate fechalanzamiento;
    private List<DesarrolladorResponseDTO> desarrolladorResponseDTOList;

}
