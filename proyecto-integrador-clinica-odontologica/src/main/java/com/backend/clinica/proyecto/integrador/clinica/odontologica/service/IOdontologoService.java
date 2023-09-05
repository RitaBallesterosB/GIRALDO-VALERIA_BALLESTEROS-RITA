package com.backend.clinica.proyecto.integrador.clinica.odontologica.service;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    List<OdontologoSalidaDto> listarOdontologos();


    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) ;

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto);

}
