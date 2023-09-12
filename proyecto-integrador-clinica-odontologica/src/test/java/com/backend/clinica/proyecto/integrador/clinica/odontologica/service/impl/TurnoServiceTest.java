package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;


import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    /*private OdontologoService odontologoService;
    private PacienteService pacienteService;

    OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("AB-1234567", "David", "Gutierrez");
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

    PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Bart", "Simpson", 1234, LocalDate.of(2023, 12, 9), new DomicilioEntradaDto("calle", 1232, "localidad", "provincia"));
    PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

    @Test
    @Order (2)
    void deberiaRegistrarUnTurno() throws BadRequestException {


        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L,LocalDateTime.of(2023, 9, 12, 10, 0) );
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertNotNull(turnoSalidaDto);
        assertEquals(LocalDateTime.of(2023, 9, 12, 10, 0), turnoSalidaDto.getFechaYHora());
        assertEquals(1L, turnoSalidaDto.getPacienteTurnoSalidaDto().getId());
        assertEquals(1L, turnoSalidaDto.getOdontologoTurnoSalidaDto().getId());
    }*/

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
    //@Order (1)
    void alIntentarEliminarUnTurnoNoExistente_deberiaLanzarseUnResourceNotFoundException(){
        try{
            turnoService.eliminarTurno(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }
}