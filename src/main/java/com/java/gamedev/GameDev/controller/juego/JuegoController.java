package com.java.gamedev.GameDev.controller.juego;

import com.java.gamedev.GameDev.model.dto.JuegoDTO;

import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/juego")
@RequiredArgsConstructor
@Slf4j
public class JuegoController {
    //IoC Inversion de control
    JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }


    @GetMapping()
    public List<JuegoDTO> GetAllJuegos(){
        return juegoService.getAllJuegos();
    }

    //@PostMapping()
    //@PutMapping("/{idJuego}")
    //@DeleteMapping("/{idJuego}")
    //@GetMapping("/{idJuego}")
}
