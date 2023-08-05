package com.java.gamedev.GameDev.repository.desarrollador;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, UUID> {

    List<Desarrollador> findByNombreIgnoreCase(String nombre);

    @Query(value ="SELECT * FROM desarrollador AS d WHERE d.nombre like %:nombre% ",nativeQuery = true)
    List<Desarrollador> findDesarrolladoByNombre( @Param("nombre") String nombre    );

}
