package com.java.gamedev.GameDev.model.dto;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDTO {
    private String iddesarrollador;
    private String idjuego;
    private String fechaLimite;
    private String descripcion;
    private String estado;
    private Desarrollador desarrollador;
    private Juego juego;
}
