package com.java.gamedev.GameDev.repository.tarea;

import com.java.gamedev.GameDev.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {

    @Override
    Optional<Tarea> findById(UUID uuid);
}
