package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.entity.Paciente;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.repository.PacienteRepository;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }


    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacGuardado = pacienteRepository.save(modelMapper.map(paciente, Paciente.class));
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacGuardado, PacienteSalidaDto.class);
        LOGGER.info("Paciente guardado: {}", pacienteSalidaDto);
        return pacienteSalidaDto;
    }

    @Override

    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) {
        Paciente pacienteRecibido = modelMapper.map(pacienteModificado, Paciente.class);
        Paciente pacienteAModificar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;

        if (pacienteAModificar != null) {

            pacienteAModificar = pacienteRecibido;
            pacienteRepository.save(pacienteAModificar);

            pacienteSalidaDto = modelMapper.map(pacienteAModificar, PacienteSalidaDto.class);

            LOGGER.warn("Odontologo actualizado: {}", pacienteSalidaDto);

        } else LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");


        return pacienteSalidaDto;
    }



    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        PacienteSalidaDto pacienteSalidaDto = null;
        if(pacienteBuscado != null){
            pacienteSalidaDto = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", pacienteSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteSalidaDto;
    }

    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacientes = pacienteRepository.findAll().stream()
                .map(o -> modelMapper.map(o, PacienteSalidaDto.class)).toList();

        LOGGER.info("Listado de todos los pacientes: {}", pacientes);

        return pacientes;
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }

    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));

    }

    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente) {
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }
}
