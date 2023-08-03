package com.java.gamedev.GameDev.domain;

import com.java.gamedev.GameDev.enumeration.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Desarrollador {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false)
    private String nombre;

    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    //@ManyToOne
    //private Juego juego;

    //@OneToMany(mappedBy = "desarrollador",cascade = CascadeType.ALL)
    //private List<Juego> juegos = new ArrayList<>();


}
