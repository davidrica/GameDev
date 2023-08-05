package com.java.gamedev.GameDev.service.desarrollador.impl;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.mapper.desarrollador.DesarrolladorResponseMapper;
import com.java.gamedev.GameDev.model.dto.DesarrolladorDTO;
import com.java.gamedev.GameDev.model.dto.DesarrolladorResponseDTO;
import com.java.gamedev.GameDev.model.dto.JuegoDTO;
import com.java.gamedev.GameDev.repository.desarrollador.DesarrolladorRepository;
import com.java.gamedev.GameDev.service.desarrollador.DesarrolladorService;
import com.java.gamedev.GameDev.service.juego.JuegoService;
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
public class DesarrolladorServiceJPAImpl implements DesarrolladorService {
    DesarrolladorRepository desarrolladorRepository;
    DesarrolladorResponseMapper desarrolladorResponseMapper;
    @Autowired
    public DesarrolladorServiceJPAImpl(DesarrolladorRepository desarrolladorRepository,DesarrolladorResponseMapper desarrolladorResponseMapper) {

        this.desarrolladorRepository = desarrolladorRepository;
        this.desarrolladorResponseMapper = desarrolladorResponseMapper;
    }


    @Override
    public List<DesarrolladorResponseDTO> getAllDesarrolladores(String nombre) {
        List<DesarrolladorResponseDTO> desarrolladorResponseDTOList = new ArrayList<>();
        List<Desarrollador> desarrolladorList = new ArrayList<>();

        desarrolladorList = desarrolladorRepository.findDesarrolladoByNombre(nombre);

        for(Desarrollador desarrollador: desarrolladorList ) {

            desarrolladorResponseDTOList.add(desarrolladorResponseMapper.desarrolladorToDesarrolladorResponseDTO(desarrollador));


        }

        return desarrolladorResponseDTOList;

    }

    @Override
    public Optional<DesarrolladorResponseDTO> getDesarrolladorById(UUID uuid) {
        Optional<Desarrollador> desarrollador = desarrolladorRepository.findById(uuid);


        if (desarrollador.isPresent()){

            return Optional.of(desarrolladorResponseMapper.desarrolladorToDesarrolladorResponseDTO(desarrollador.get()));



        }

        return Optional.empty();
    }

    @Override
    public Desarrollador crearDesarrollador(DesarrolladorResponseDTO desarrolladorResponseDTO) {
        Desarrollador desarrollador = desarrolladorResponseMapper.desarrolladorResponseDtoToDesarrollador(desarrolladorResponseDTO);

        return desarrolladorRepository.save(desarrollador);

    }

    @Override
    public boolean borrarDesarrollador(UUID uuid) {
        if (desarrolladorRepository.existsById(uuid)){
            desarrolladorRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Override
    public Desarrollador actualizarDesarrollador(UUID uuid, DesarrolladorResponseDTO desarrolladorResponseDTO) {

        Optional<Desarrollador>  desarrollador= desarrolladorRepository.findById(uuid);


        if (desarrollador.isPresent()){
            updating(desarrollador.get(),desarrolladorResponseDTO);

            return desarrolladorRepository.saveAndFlush(desarrollador.get());


        }

        return desarrollador.get();
    }
    private void updating(Desarrollador desarrollador,DesarrolladorResponseDTO desarrolladorResponseDTO){
        if (!desarrolladorResponseDTO.getNombre().isBlank()){
            desarrollador.setNombre(desarrolladorResponseDTO.getNombre());
        }
        if (!desarrolladorResponseDTO.getEmail().isBlank()){
            desarrollador.setEmail(desarrolladorResponseDTO.getEmail());
        }
        if (!desarrolladorResponseDTO.getRol().toString().isBlank() )
            desarrollador.setRol(desarrolladorResponseDTO.getRol());
        }


}
