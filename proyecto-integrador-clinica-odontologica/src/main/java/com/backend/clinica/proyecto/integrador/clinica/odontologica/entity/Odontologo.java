package com.backend.clinica.proyecto.integrador.clinica.odontologica.entity;

import javax.persistence.*;

@Entity
@Table (name = "ODONTOLOGOS")

public class Odontologo {

    @Id
    @GeneratedValue( strategy =
    GenerationType.IDENTITY)

    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    public Odontologo() {// conversion - serializacion y desearilizaciion OBJETO - JSON
    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }
    // SE DEBE BORRAR
    //public void setId(Long id) {
        //this.id = id;
    //}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}