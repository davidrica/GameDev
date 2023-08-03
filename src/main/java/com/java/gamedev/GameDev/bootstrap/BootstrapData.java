package com.java.gamedev.GameDev.bootstrap;

import com.java.gamedev.GameDev.domain.Desarrollador;
import com.java.gamedev.GameDev.domain.Juego;
import com.java.gamedev.GameDev.domain.Tarea;
import com.java.gamedev.GameDev.enumeration.EstadoEnum;
import com.java.gamedev.GameDev.enumeration.RolEnum;
import com.java.gamedev.GameDev.model.csv.desarrollador.DesarrolladorCsvRecord;
import com.java.gamedev.GameDev.model.csv.juego.JuegoCsvRecord;
import com.java.gamedev.GameDev.model.csv.tarea.TareaCsvRecord;
import com.java.gamedev.GameDev.repository.desarrollador.DesarrolladorRepository;
import com.java.gamedev.GameDev.repository.juego.JuegoRepository;
import com.java.gamedev.GameDev.repository.tarea.TareaRepository;
import com.java.gamedev.GameDev.service.csv.desarrollador.DesarrolladorCsvService;

import com.java.gamedev.GameDev.service.csv.juego.JuegoCsvService;
import com.java.gamedev.GameDev.service.csv.tarea.TareaCsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapData implements CommandLineRunner {
    //tareas
    private final TareaRepository tareaRepository;
    private final TareaCsvService tareaCsvService;
    //juegos
    private final JuegoRepository juegoRepository;
    private final JuegoCsvService juegoCsvService;

    //Desarrollador
    private final DesarrolladorRepository desarrolladorRepository;
    private final DesarrolladorCsvService desarrolladorCsvService;
    public void run(String... args) throws Exception {
        log.info("Cargando datos");
        loadDesarrolladores();
        loadJuegos();
        loadTareas();
    }
    private void loadDesarrolladores () throws FileNotFoundException{
        if (desarrolladorRepository.count()<50){

            File file = ResourceUtils.getFile("classpath:csvdata/dev.csv");
            List<DesarrolladorCsvRecord> desarrolladorCsvRecordList = desarrolladorCsvService.importarCsv(file);

            if (!desarrolladorCsvRecordList.isEmpty()){
                log.info("Cargando base de datos 'Desarrolladores...'");
                for (DesarrolladorCsvRecord desarollador: desarrolladorCsvRecordList) {
                    desarrolladorRepository.save(
                            Desarrollador.builder()
                                    .uuid(UUID.randomUUID())
                                    .nombre(desarollador.getNombre())
                                    .email(desarollador.getEmail())
                                    .rol(RolEnum.valueOf(desarollador.getRol()))
                                    .build()
                    );
                }
            }
        }

    }
    private void loadJuegos() throws FileNotFoundException {
        List<Desarrollador> desarrolladorList = desarrolladorRepository.findAll();
        if (juegoRepository.count() < 50 && desarrolladorList.size() >= 50) {
            File file = ResourceUtils.getFile("classpath:csvdata/games.csv");
            List<JuegoCsvRecord> juegoCsvRecordList = juegoCsvService.importarCsv(file);
            log.info("Cargando base de Juegos con desarrolladores");

            for (int i = 0; i < 50; i++) {
                juegoRepository.save(
                        getNuevoJuego(desarrolladorList.get(i), juegoCsvRecordList.get(i))
                );
            }
        }

    }
    private Juego getNuevoJuego(Desarrollador desarrollador, JuegoCsvRecord juegoCsvRecord){
        return Juego.builder()
                .uuid(UUID.randomUUID())
                .titulo(juegoCsvRecord.getTitulo() )
                .descripcion(juegoCsvRecord.getDescripcion())
                .fechaLanzamiento(LocalDate.parse(juegoCsvRecord.getFechaLanzamiento() , DateTimeFormatter.ofPattern("M/d/yyyy")))
                .desarrolladores(List.of(desarrollador ))
                //.categories(List.of(categoryRepository.findByNameIgnoreCase(bookCsvV2Record.getNameCategory()).get()))

                .build();


    }

    private void loadTareas () throws FileNotFoundException{
        List<Desarrollador> desarrolladorList = desarrolladorRepository.findAll();
        List<Juego> juegoList = juegoRepository.findAll();

        if (juegoList.size() >= 50 && desarrolladorList.size() >= 50 ) {
            File file = ResourceUtils.getFile("classpath:csvdata/tareas.csv");
            List<TareaCsvRecord> tareaCsvRecordList = tareaCsvService.importarCsv(file);
            log.info("Cargando tareas");

            for (int i = 0; i < 50; i++) {
                tareaRepository.save(
                        getNuevaTarea(desarrolladorList.get(i),juegoList.get(i), tareaCsvRecordList.get(i))
                );
            }
        }


    }

    private Tarea getNuevaTarea(Desarrollador desarrollador,Juego juego , TareaCsvRecord tareaCsvRecord){
        return Tarea.builder()
                .uuid(UUID.randomUUID())
                .descripcion(tareaCsvRecord.getDescripcion())
                .estado(EstadoEnum.valueOf(tareaCsvRecord.getEstado()))
                .fehcaLimite(LocalDate.parse(tareaCsvRecord.getFehcaLimite() , DateTimeFormatter.ofPattern("M/d/yyyy")))
                .desarrollador(desarrollador)
                .juego(juego)
                .build();
    }
}
