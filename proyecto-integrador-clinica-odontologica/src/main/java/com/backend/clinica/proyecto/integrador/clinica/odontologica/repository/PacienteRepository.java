package com.backend.clinica.proyecto.integrador.clinica.odontologica.repository;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Long> {
}
