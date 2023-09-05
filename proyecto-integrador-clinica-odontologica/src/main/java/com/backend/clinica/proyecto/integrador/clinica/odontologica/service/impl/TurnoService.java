package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.TurnoModificadoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.entity.Turno;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
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
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;

        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBdd);
                throw new BadRequestException(pacienteNoEnBdd);
            } else {
                LOGGER.error(odontologoNoEnBdd);
                throw new BadRequestException(odontologoNoEnBdd);
            }
        } else {
            Turno turnoNuevo = turnoRepository.save(modelMapper.map(turnoEntradaDto, Turno.class));
            turnoSalidaDto = entidadADto(turnoNuevo);
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
        }
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


    public void eliminarTurno (Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) !=null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con Id {}", id);
        }else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
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

    private void configureMappings() {
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteTurnoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoTurnoSalidaDto));
    }

    public TurnoSalidaDto entidadADto(Turno turno) {
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }
}