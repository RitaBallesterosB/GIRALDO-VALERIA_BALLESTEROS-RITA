package com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties (ignoreUnknown = true)
public class TurnoModificadoEntradaDto {
    @NotNull
    private Long id;

    @NotNull
    private OdontologoModificacionEntradaDto odontologo;

    @NotNull
    private PacienteModificacionEntradaDto paciente;

    @NotNull
    private LocalDateTime fechaYHora;

    public TurnoModificadoEntradaDto() {
    }

    public TurnoModificadoEntradaDto(Long id, OdontologoModificacionEntradaDto odontologo, PacienteModificacionEntradaDto paciente, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OdontologoModificacionEntradaDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoModificacionEntradaDto odontologo) {
        this.odontologo = odontologo;
    }

    public PacienteModificacionEntradaDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModificacionEntradaDto paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
