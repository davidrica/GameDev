package com.java.gamedev.GameDev.domain;

import com.java.gamedev.GameDev.enumeration.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Tarea {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false)
    private String descripcion;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Juego juego;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Desarrollador desarrollador;

    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;


    @Override
    public String toString() {
        return "{" +
                "'uuid':'" + uuid +'\'' +
                ", 'titulo':'" + descripcion + '\'' +
                ", 'Juego':'" +juego.getTitulo() + '\'' +
                ", 'desarrollador':'" +desarrollador.getNombre() + '\'' +
                ", 'Fecha limite':'" + getFechaLimite() +'\'' +
                '}';
    }

}
