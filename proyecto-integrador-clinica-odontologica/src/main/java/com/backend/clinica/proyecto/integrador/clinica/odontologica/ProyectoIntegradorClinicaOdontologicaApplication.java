package com.backend.clinica.proyecto.integrador.clinica.odontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class ProyectoIntegradorClinicaOdontologicaApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProyectoIntegradorClinicaOdontologicaApplication.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(ProyectoIntegradorClinicaOdontologicaApplication.class, args);

        LOGGER.info("ProyectoIntegradorClinicaOdontologica is now running...");

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
