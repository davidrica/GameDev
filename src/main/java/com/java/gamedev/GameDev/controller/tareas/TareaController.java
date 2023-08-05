package com.java.gamedev.GameDev.controller.tareas;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.model.dto.TareaDTO;
import com.java.gamedev.GameDev.model.dto.TareaResponseDTO;
import com.java.gamedev.GameDev.service.desarrollador.DesarrolladorService;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import com.java.gamedev.GameDev.service.tarea.TareaService;
import lombok.Getter;
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
@RequestMapping("/api/v1/tarea")
@RequiredArgsConstructor
@Slf4j
public class TareaController {

    TareaService tareaService;
    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping()
    public List<TareaResponseDTO> getAllTareas(
            @RequestParam(required = false,name = "idTarea" ,defaultValue = "*") String idTarea,
            @RequestParam(required = false,name = "idDesarrollador" ,defaultValue = "*" ) String idDesarrollador,
            @RequestParam(required = false,name = "idJuego",defaultValue = "*") String idJuego,
            @RequestParam(required = false,name = "estado",defaultValue = "*") String estado,
            @RequestParam(required = false,name = "plazo",defaultValue = "*") String plazo


            ){

        return tareaService.getAllTareas(idTarea,idDesarrollador,idJuego,estado,plazo);
    }

    @PutMapping("/{idTarea}/{estado}")
    public TareaResponseDTO actualizarJuego(
            @PathVariable(value = "idTarea")UUID idTarea,
            @PathVariable(value = "estado") String estado
    )
            throws ChangeSetPersister.NotFoundException {




        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ idTarea);


        return  tareaService.actualizarEstado(idTarea,estado).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }
    @PostMapping()
    public ResponseEntity crearTarea(@RequestBody TareaDTO tareaDTO)throws ChangeSetPersister.NotFoundException {
        log.info("============================================ResponseEntity crearTarea");
        Tarea tarea = tareaService.crearTarea(tareaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/juego/"+ tarea.getUuid());


        return new ResponseEntity(tarea.toString(),headers, HttpStatus.CREATED);
    }
}
