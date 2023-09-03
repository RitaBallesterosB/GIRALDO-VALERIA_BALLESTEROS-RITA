package com.backend.clinica.proyecto.integrador.clinica.odontologica.service;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.TurnoModificadoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto);
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id);
    TurnoSalidaDto modificarTurno(TurnoModificadoEntradaDto turnoModificado);

}
