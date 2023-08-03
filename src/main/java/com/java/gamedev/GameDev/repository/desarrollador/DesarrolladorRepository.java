package com.java.gamedev.GameDev.repository.desarrollador;

import com.java.gamedev.GameDev.domain.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, UUID> {

    Optional<Desarrollador> findByNombreIgnoreCase(String nombre);
}
