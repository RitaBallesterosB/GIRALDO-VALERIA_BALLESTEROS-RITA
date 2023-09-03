package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.TurnoModificadoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.entity.Turno;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.repository.TurnoRepository;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) {
        Turno turGuardado =  turnoRepository.save(modelMapper.map(turno, Turno.class));
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turGuardado, TurnoSalidaDto.class);
        LOGGER.info ("Turno guardado : {}", turnoSalidaDto);
        return turnoSalidaDto;
    }


    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll().stream().map(t -> modelMapper.map(t, TurnoSalidaDto.class)).toList();

        LOGGER.info ("Listado de todos los turnos: {}", turnos);
        return turnos;
    }


    public TurnoSalidaDto buscarTurnoPorId(Long id) {

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;
        if (turnoBuscado != null){
            turnoSalidaDto = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info ("Turno Encontrado: {}", turnoSalidaDto);

        }else LOGGER.error("El ID no se encuentra registrado en la base de datos");
        return  turnoSalidaDto;
    }


    public void eliminarTurno (Long id) {
        if (buscarTurnoPorId(id) !=null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con Id {}", id);
        }else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
        }

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificadoEntradaDto turnoModificado) {
        Turno turnoRecibido = modelMapper.map(turnoModificado, Turno.class);
        Turno turnoAModificar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAModificar != null){
            turnoAModificar = turnoRecibido;
            turnoRepository.save(turnoAModificar);

            turnoSalidaDto = modelMapper.map(turnoAModificar, TurnoSalidaDto.class);
            LOGGER.warn("Turno Modificado: {]", turnoSalidaDto );

        }else LOGGER.error("No fue posible modificar el turno");
        return turnoSalidaDto;
    }

}