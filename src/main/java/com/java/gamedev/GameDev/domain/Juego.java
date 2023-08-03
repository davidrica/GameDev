package com.java.gamedev.GameDev.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Juego {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID uuid;

    @Column(length = 150, columnDefinition = "varchar(150)", updatable = true, nullable = false)
    private String titulo;

    @Column(length = 150, columnDefinition = "varchar(150)", updatable = true, nullable = false)
    private String descripcion;


    private LocalDate fechaLanzamiento;
    //muchos a uno
    //@ManyToOne
    //private Desarrollador desarrollador;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "juegos_desarrolladores",joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id"))
    private List<Desarrollador> desarrolladores = new ArrayList<>();


    //@Builder.Default
    //@ManyToMany
    //@JoinTable(name = "juegos_tareas",joinColumns = @JoinColumn(name = "juego_id"),
   //         inverseJoinColumns = @JoinColumn(name = "tarea_id"))
   // private List<Tarea> tareas = new ArrayList<>();


}

