package com.java.gamedev.GameDev.service.tarea.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.enumeration.EstadoEnum;
import com.java.gamedev.GameDev.mapper.desarrollador.DesarrolladorResponseMapper;
import com.java.gamedev.GameDev.mapper.juego.JuegoResponseMapper;
import com.java.gamedev.GameDev.mapper.tarea.TareaMapper;
import com.java.gamedev.GameDev.mapper.tarea.TareaResponseMapper;
import com.java.gamedev.GameDev.model.dto.DesarrolladorDTO;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.TareaDTO;
import com.java.gamedev.GameDev.model.dto.TareaResponseDTO;
import com.java.gamedev.GameDev.repository.desarrollador.DesarrolladorRepository;
import com.java.gamedev.GameDev.repository.juego.JuegoRepository;
import com.java.gamedev.GameDev.repository.tarea.TareaRepository;
import com.java.gamedev.GameDev.service.tarea.TareaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Slf4j
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {
    TareaRepository tareaRepository;
    TareaResponseMapper tareaResponseMapper;
    TareaMapper tareaMapper;
    DesarrolladorResponseMapper desarrolladorResponseMapper;
    JuegoResponseMapper juegoResponseMapper;
    DesarrolladorRepository desarrolladorRepository;
    JuegoRepository juegoRepository;


    @Autowired
    public TareaServiceImpl( TareaMapper tareaMapper,
            TareaRepository tareaRepository,TareaResponseMapper tareaResponseMapper,
            DesarrolladorRepository desarrolladorRepository,DesarrolladorResponseMapper desarrolladorResponseMapper,
            JuegoRepository juegoRepository,JuegoResponseMapper juegoResponseMapper) {
        this.tareaRepository = tareaRepository;
        this.tareaResponseMapper =tareaResponseMapper;
        this.desarrolladorRepository =desarrolladorRepository;
        this.desarrolladorResponseMapper =desarrolladorResponseMapper;
        this.juegoRepository =juegoRepository;
        this.juegoResponseMapper =juegoResponseMapper;
        this.tareaMapper =tareaMapper;
    }




    @Override
    public List<TareaResponseDTO> getAllTareas(String idTarea, String idDesarrollador,String idJuego,String estado,String plazo) {
        List<TareaResponseDTO> tareaResponseDTOList = new ArrayList<>();
        List<Tarea> tareaList = new ArrayList<>();
        boolean todas = true;
        String where ="";
        log.info("===============idTarea============== "+idTarea);
        log.info("===============idDesarrollador====== "+idDesarrollador);
        log.info("===============idJuego============== "+idJuego);
        log.info("===============estado=============== "+estado);
        log.info("===============plazo=============== "+plazo);
        try{
            if (!idDesarrollador.contains("*") ){
                //where = " t.desarrollador_uuid = :desarrollador_uuid ";
                tareaList= tareaRepository.findTareasPorDesarrolladorById(idDesarrollador);
                todas =false;
            }

            if (!idTarea.contains("*") ){
                //where = " t.uuid = :estado ";
                tareaList= tareaRepository.findTareasById(idTarea);
                todas =false;
            }



            if (!idJuego.contains("*") ){
                //where = " t.juego_uuid = " + idJuego;
                tareaList= tareaRepository.findTareasPorJuegoById(idJuego);
                todas =false;
            }
            if (!estado.contains("*") ){
                //where = " t.estado = :estado ";
                tareaList= tareaRepository.findTareasPorEstado(estado);
                todas =false;
            }
            if (plazo.contains("S") ){
                LocalDate ahora = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
                String Fechafinalizado= formatter.format(ahora);

                //where = " t.estado = :estado ";
                tareaList= tareaRepository.findTareasPorEstado(Fechafinalizado);

                todas =false;
            }
            if (todas){
                tareaList= tareaRepository.findAll();
            }

            for(Tarea tarea: tareaList ) {
                tareaResponseDTOList.add(tareaResponseMapper.tareaToTareaResponseDTO(tarea));

            }


        } catch (IllegalArgumentException exception){
            //handle the case where string is not valid UUID
            log.info(exception.toString());
        }


        return tareaResponseDTOList;
    }

    @Override
    public Optional<TareaResponseDTO> actualizarEstado(UUID uuid, String estado) {

        Optional<Tarea> tarea = tareaRepository.findById(uuid);


        if (tarea.isPresent()){
            tarea.get().setEstado(EstadoEnum.valueOf(estado));
            tareaRepository.save(tarea.get());
            return Optional.of(tareaResponseMapper.tareaToTareaResponseDTO(tarea.get()));




        }

        return Optional.empty();

    }

    @Override
    public Tarea crearTarea(TareaDTO tareaDTO) {
        log.info("============================================Tarea crearTarea");
        Optional<Desarrollador>  desarrollador= desarrolladorRepository.findById(  UUID.fromString(tareaDTO.getIddesarrollador())         );
        Optional<Juego> juego= juegoRepository.findById(UUID.fromString(tareaDTO.getIdjuego())         );
        Tarea tarea = tareaMapper.tareaDTOToTarea(tareaDTO);

        if (desarrollador.isPresent() && juego.isPresent()){

            tarea.setDesarrollador(desarrollador.get());
            tarea.setJuego(juego.get());

        }

        return tareaRepository.save(tarea);

    }
}
