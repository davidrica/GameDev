package com.java.gamedev.GameDev.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaResponseDTO {
    private String idtarea;
    private String iddesarrollador;
    private String idjuego;
    private String fechaLimite;
    private String descripcion;
    private String estado;
    private DesarrolladorResponseDTO desarrolladorResponseDTO;
    private JuegoResponseDTO juegoResponseDTO;

}
