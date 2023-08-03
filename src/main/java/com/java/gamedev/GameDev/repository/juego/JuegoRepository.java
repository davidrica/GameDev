package com.java.gamedev.GameDev.repository.juego;

import com.java.gamedev.GameDev.domain.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JuegoRepository extends JpaRepository<Juego, UUID> {
    Optional<Juego> findByTituloIgnoreCase(String titulo);

}
