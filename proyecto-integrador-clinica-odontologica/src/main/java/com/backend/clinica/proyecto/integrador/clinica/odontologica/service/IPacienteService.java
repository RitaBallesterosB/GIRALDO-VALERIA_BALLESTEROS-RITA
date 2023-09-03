package com.backend.clinica.proyecto.integrador.clinica.odontologica.service;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id);

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado);


}
