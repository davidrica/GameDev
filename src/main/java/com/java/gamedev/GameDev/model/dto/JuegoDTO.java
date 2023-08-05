package com.java.gamedev.GameDev.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JuegoDTO {
    private String UUII;
    private String titulo;
    private String descripcion;
    private String fechaLanzamiento;
}
