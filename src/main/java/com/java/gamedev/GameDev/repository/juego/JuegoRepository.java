package com.java.gamedev.GameDev.repository.juego;

import com.java.gamedev.GameDev.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface JuegoRepository extends JpaRepository<Juego, UUID> {
    final String sql_juego_desarrolladores ="SELECT * FROM juego AS j" +
            " INNER JOIN juegos_desarrolladores jd ON (j.uuid=jd.juego_id) " +
            " INNER JOIN desarrollador d ON (jd.desarrollador_id=d.uuid) " ;

    List<Juego> findByTituloEqualsIgnoreCase(String titulo);

    @Query(value ="SELECT * FROM juego AS j WHERE j.titulo like %:titulo% ",nativeQuery = true)
    List<Juego> findJuegosByTitulo( @Param("titulo") String title    );

    @Query(value ="SELECT * FROM juego AS j WHERE j.titulo like %:titulo% AND fecha_lanzamiento <= :finalizado",nativeQuery = true)
    List<Juego> findJuegosByTituloAndFinalizado(
            @Param("titulo") String title,
            @Param("finalizado") String finalizado
    );
    @Query(value = "SELECT * FROM juego AS j WHERE j.titulo like %:titulo% AND fecha_lanzamiento  > :finalizado " ,nativeQuery = true)
    List<Juego> findJuegosByTituloAndNoFinalizado(
            @Param("titulo") String title,
            @Param("finalizado") String finalizado
    );

    @Query(value = "SELECT * FROM juego AS j WHERE j.uuid = :idjuego ",nativeQuery = true)
    Optional<Juego> findJuegosById(
            @Param("idjuego") UUID idjuego    );



}
