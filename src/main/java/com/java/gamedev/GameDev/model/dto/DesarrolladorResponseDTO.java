package com.java.gamedev.GameDev.model.dto;

import com.java.gamedev.GameDev.enumeration.RolEnum;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesarrolladorResponseDTO {
    private String uuid;
    private String nombre;
    private String email;
    private RolEnum rol;

}
