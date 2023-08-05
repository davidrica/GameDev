package com.java.gamedev.GameDev.controller.desarrollador;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.model.dto.DesarrolladorDTO;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.service.desarrollador.DesarrolladorService;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/desarrollador")
@RequiredArgsConstructor
@Slf4j
public class DesarrolladorControler {
    DesarrolladorService desarrolladorService;
    @Autowired
    public DesarrolladorControler(DesarrolladorService desarrolladorService) {

        this.desarrolladorService = desarrolladorService;
    }
    @PostMapping()
    public ResponseEntity crearDesarrollador(@RequestBody DesarrolladorResponseDTO desarrolladorResponseDTO)throws ChangeSetPersister.NotFoundException {
        log.info("Creacion de un nuevo Juego");
        Desarrollador desarrollador = desarrolladorService.crearDesarrollador(desarrolladorResponseDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/desarrollador/"+ desarrollador.getUuid());


        return new ResponseEntity(desarrollador.toString(),headers, HttpStatus.CREATED);
    }
    @GetMapping()
    public List<DesarrolladorResponseDTO> getAllBooks(
            @RequestParam(required = false,name = "nombre",defaultValue = "")     String nombre
    ){

        return desarrolladorService.getAllDesarrolladores(nombre);
    }
    @GetMapping("/{idDesarrollador}")
    public DesarrolladorResponseDTO getDesarrolladorById(
            @PathVariable(value = "idDesarrollador") UUID idDesarrollador
    ) throws ChangeSetPersister.NotFoundException {

        return desarrolladorService.getDesarrolladorById(idDesarrollador).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }



    @DeleteMapping("/{idDesarrollador}")
    public ResponseEntity borrarDesarrollador(
            @PathVariable(value = "idDesarrollador")UUID idDesarrollador
    ) throws ChangeSetPersister.NotFoundException {
        boolean borrado = desarrolladorService.borrarDesarrollador(idDesarrollador);

        if(borrado){
            log.info("desarrollador eliminado");
            throw new ChangeSetPersister.NotFoundException();

        }else {
            log.warn("Desarrollador no encontrado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{idDesarrollador}")
    public ResponseEntity actualizarDesarrollador(
            @PathVariable(value = "idDesarrollador")UUID idDesarrollador,
            @RequestBody DesarrolladorResponseDTO desarrolladorResponseDTO
    )
            throws ChangeSetPersister.NotFoundException {
        Desarrollador desarrollador = desarrolladorService.actualizarDesarrollador(idDesarrollador,desarrolladorResponseDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ idDesarrollador);


        return  new ResponseEntity(desarrollador.toString(),headers, HttpStatus.CREATED);

    }
}
