package com.java.gamedev.GameDev.service.juego.impl;

import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegoServiceJPAImpl implements JuegoService {
    @Override
    public List<JuegoDTO> getAllJuegos() {
        return null;
    }
}
