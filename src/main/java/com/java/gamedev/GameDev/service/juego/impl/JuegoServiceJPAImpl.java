package com.java.gamedev.GameDev.service.juego.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.mapper.juego.JuegoMapper;
import com.java.gamedev.GameDev.mapper.juego.JuegoResponseMapper;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.model.dto.JuegoResponseDTO;
import com.java.gamedev.GameDev.repository.desarrollador.DesarrolladorRepository;
import com.java.gamedev.GameDev.repository.juego.JuegoRepository;
import com.java.gamedev.GameDev.service.juego.JuegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
public class JuegoServiceJPAImpl implements JuegoService {
    DesarrolladorRepository desarrolladorRepository;
    JuegoRepository juegoRepository;

    JuegoMapper juegoMapper;
    JuegoResponseMapper juegoResponseMapper;

    @Autowired
    public JuegoServiceJPAImpl(
            DesarrolladorRepository desarrolladorRepository,
            JuegoRepository juegoRepository,
            JuegoMapper juegoMapper,
            JuegoResponseMapper juegoResponseMapper) {

        this.juegoResponseMapper = juegoResponseMapper;
        this.juegoMapper = juegoMapper;
        this.juegoRepository = juegoRepository;
        this.desarrolladorRepository = desarrolladorRepository;
    }

    public List<JuegoDTO> getAllJuegos(String titulo ,String finalizado ) {
        List<JuegoDTO> juegoDTOList = new ArrayList<>();
        List<Juego> juegoList = new ArrayList<>();



        //if (!titulo.isBlank() ) {
            LocalDate ahora = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
            String Fechafinalizado= formatter.format(ahora);

            //filtro si finalizado es FeCHA

            if (finalizado.contains("S") ){
                //titulo de los juegos y finalizados
                juegoList = juegoRepository.findJuegosByTituloAndFinalizado(titulo,Fechafinalizado);
            }else{
                //titulo de los juegos - y no finalizados
                juegoList = juegoRepository.findJuegosByTituloAndNoFinalizado(titulo,Fechafinalizado);
            }
        //}else{
            //todos los juegos
           // juegoList= juegoRepository.findAll();
       // }

        for(Juego juego: juegoList ) {

            juegoDTOList.add(juegoMapper.juegoToJuegoDto(juego));


        }

        return juegoDTOList;
    }

    @Override
    public Optional<JuegoResponseDTO> getJuegoById(UUID uuid) {

        Optional<Juego> juego = juegoRepository.findById(uuid);


        if (juego.isPresent()){

            return Optional.of(juegoResponseMapper.juegoToJuegosReponseDTO(juego.get()));
            //return Optional.of(juegoMapper.juegoToJuegoDto(juego.get()));


        }

        return Optional.empty();
    }

    @Override
    public Juego crearJuego(JuegoDTO juegoDTO) {
        log.debug("----------------------creando juego");
        Juego juego = juegoMapper.juegoDtoToJuego(juegoDTO);

        return juegoRepository.save(juego);
    }

    @Override
    public boolean borrarJuego(UUID uuid) {
        if (juegoRepository.existsById(uuid)){
            juegoRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Override
    public Juego actualizarJuego(UUID uuid, JuegoDTO juegoDTO) {

        Optional<Juego> juego = juegoRepository.findById(uuid);


        if (juego.isPresent()){
            updating(juego.get(),juegoDTO);

            return juegoRepository.saveAndFlush(juego.get());
            //return Optional.of(juegoMapper.juegoToJuegoDto(juego.get()));


        }

        return juego.get();


    }

    @Override
    public Optional<JuegoResponseDTO> asignarDesarrollador(UUID uuidJuego, UUID uuidDesarrollador) {
        Optional<Juego> juego = juegoRepository.findById(uuidJuego);
        Optional<Desarrollador> desarrollador = desarrolladorRepository.findById(uuidDesarrollador);
        if (juego.isPresent() && desarrollador.isPresent()){
            juego.get().getDesarrolladores().add(desarrollador.get());
            juegoRepository.save(juego.get());
            return Optional.of(juegoResponseMapper.juegoToJuegosReponseDTO(juego.get()));
        }
        //si juego no existe
        return Optional.empty();



    }

    private void updating(Juego juego,JuegoDTO juagoActualizado){
        if (!juagoActualizado.getDescripcion().isBlank()){
            juego.setDescripcion(juagoActualizado.getDescripcion());
        }
        if (!juagoActualizado.getTitulo().isBlank()){
            juego.setTitulo(juagoActualizado.getTitulo());
        }
        if (!juagoActualizado.getFechaLanzamiento().isBlank()){
            juego.setDescripcion(juagoActualizado.getFechaLanzamiento());
        }

    }
}
