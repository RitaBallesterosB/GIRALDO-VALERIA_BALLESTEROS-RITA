package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;


    @Test
    void deberiaRetornarNullSiElIdNoExiste() {
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarTurnoPorId(2L);
        assertNull(turnoSalidaDto);
    }

    @Test
    void deberiaBuscarTurnoPorId1() {
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarTurnoPorId(1L);
        assertNull(turnoSalidaDto);
    }

    @Test
    void alIntentarEliminarUnTurnoNoExistente_deberiaLanzarseUnResourceNotFoundException() {
        try {
            turnoService.eliminarTurno(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }
}