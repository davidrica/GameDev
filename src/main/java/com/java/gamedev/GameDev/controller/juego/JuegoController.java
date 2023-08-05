package com.java.gamedev.GameDev.controller.juego;

import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;

import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/juego")
@RequiredArgsConstructor
@Slf4j
public class JuegoController {
    //@PostMapping()
    //@PutMapping("/{idJuego}")
    //@DeleteMapping("/{idJuego}")
    //@GetMapping("/{idJuego}")
    //@GetMapping("")

    //IoC Inversion de control
    JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @PostMapping()
    public ResponseEntity crearJuego(@RequestBody JuegoDTO juegoDTO)throws ChangeSetPersister.NotFoundException {
        log.info("Creacion de un nuevo Juego");
        Juego juegoNuevo = juegoService.crearJuego(juegoDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ juegoNuevo.getUuid());


        return new ResponseEntity(juegoNuevo.toString(),headers, HttpStatus.CREATED);
    }

//    @GetMapping()
//    public List<JuegoDTO> GetAllJuegos(){
//        return juegoService.getAllJuegos(S);

//    }
    @GetMapping()
    public List<JuegoDTO> getAllBooks(
            @RequestParam(required = false,name = "titulo",defaultValue = "")     String titulo,
            @RequestParam(required = false,name = "finalizado",defaultValue = "N") String finalizado
    ){

        return juegoService.getAllJuegos(titulo,finalizado);
    }

    @GetMapping("/{idJuego}")
    //public JuegoDTO getJuegoById(
    public JuegoResponseDTO getJuegoById(
            @PathVariable(value = "idJuego") UUID idJuego
    ) throws ChangeSetPersister.NotFoundException {

        return juegoService.getJuegoById(idJuego).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @DeleteMapping("/{idJuego}")
    public ResponseEntity borrarJuego(
            @PathVariable(value = "idJuego")UUID idJuego
    ) throws ChangeSetPersister.NotFoundException {
        boolean isBookDeleted = juegoService.borrarJuego(idJuego);

        if(isBookDeleted){
            log.info("Juego eliminado");
            throw new ChangeSetPersister.NotFoundException();

        }else {
            log.warn("Juego no encontrado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    //PUT --> Actualizar un recurso
    @PutMapping("/{idJuego}/{idDesarrollador}")
    public JuegoResponseDTO actualizarJuego(
            @PathVariable(value = "idJuego")UUID idJuego,
            @PathVariable(value = "idDesarrollador") UUID idDesarrollador
    )
            throws ChangeSetPersister.NotFoundException {




        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ idJuego);


        return  juegoService.asignarDesarrollador(idJuego,idDesarrollador).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }
    @PutMapping("/{idJuego}")
    public ResponseEntity actualizarJuego(
            @PathVariable(value = "idJuego")UUID idJuego,
            @RequestBody JuegoDTO juegoActualizado
    )
            throws ChangeSetPersister.NotFoundException {
        Juego juego = juegoService.actualizarJuego(idJuego,juegoActualizado);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ idJuego);


        return  new ResponseEntity(juego.toString(),headers, HttpStatus.CREATED);

    }
}
