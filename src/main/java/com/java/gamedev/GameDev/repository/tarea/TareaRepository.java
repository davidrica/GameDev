package com.java.gamedev.GameDev.repository.tarea;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {


    @Query(value = "SELECT * FROM tarea AS t WHERE t.uuid = :uuid ",nativeQuery = true)
    List<Tarea> findTareasById(
            @Param("uuid") String uuid    );

    @Query(value = "SELECT * FROM tarea AS t WHERE t.desarrollador_uuid = :desarrollador_uuid ",nativeQuery = true)
    List<Tarea> findTareasPorDesarrolladorById(
            @Param("desarrollador_uuid") String id    );


    @Query(value = "SELECT * FROM tarea AS t WHERE t.estado = :estado ",nativeQuery = true)
    List<Tarea> findTareasPorEstado(
            @Param("estado") String estado    );

    @Query(value = "SELECT * FROM tarea AS t WHERE t.juego_uuid = :juego_uuid ",nativeQuery = true)
    List<Tarea> findTareasPorJuegoById(
            @Param("juego_uuid") String juego_uuid    );

    @Query(value = "SELECT * FROM tarea AS t WHERE :where ",nativeQuery = true)
    List<Tarea> findByCustom(
            @Param("where") String where    );


    @Query(value ="SELECT * FROM tarea AS t WHERE  fecha_limite <= :finalizado",nativeQuery = true)
    List<Juego> findTareasFechaLimit(
            @Param("finalizado") String finalizado
    );
}
