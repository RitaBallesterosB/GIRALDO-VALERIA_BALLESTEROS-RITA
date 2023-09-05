package com.backend.clinica.proyecto.integrador.clinica.odontologica.controller;

import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.modificacion.TurnoModificadoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica.proyecto.integrador.clinica.odontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);

    }

    //PUT
    @PutMapping("actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@Valid @RequestBody TurnoModificadoEntradaDto turno) {
        return new ResponseEntity<>(turnoService.modificarTurno(turno), HttpStatus.OK);
    }

    //GET
    @GetMapping("{id}")
    public ResponseEntity<TurnoSalidaDto> obtenerTurnoPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
