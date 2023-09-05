package com.backend.clinica.proyecto.integrador.clinica.odontologica.service;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado);


}
