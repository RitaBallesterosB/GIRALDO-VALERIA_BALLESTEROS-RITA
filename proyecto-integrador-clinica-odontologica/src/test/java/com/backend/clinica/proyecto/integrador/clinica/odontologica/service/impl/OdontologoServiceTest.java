package com.backend.clinica.proyecto.integrador.clinica.odontologica.service.impl;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologoDeNombreDavid(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("AB-1234567","David", "Gutierrez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("David", odontologoSalidaDto.getNombre());
        assertEquals(1, odontologoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDeOdontologos(){
        assertTrue(odontologoService.listarOdontologos().size() > 0);
    }


    @Test
    void alIntentarActualizarElOdontologoId2_deberiaLanzarseUnaResourceNotFoundException(){
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));
    }

    @Test
    @Order(3)
    void alIntentarEliminarUnOdontologoYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }




}