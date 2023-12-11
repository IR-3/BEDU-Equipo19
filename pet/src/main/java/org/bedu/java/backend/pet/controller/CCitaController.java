package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.service.CCitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Endpoint de Citas", description = "CRUD de citas")
@RestController
@RequestMapping("citas")
public class CCitaController {

  @Autowired
  private CCitaService Service;

  @Operation(summary = "Obtiene la lista de todos las citas")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CCitaDTO> RegresarLista() {
    return Service.RegresarLista();
  }

  @Operation(summary = "Crea una nueva cita")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CCitaDTO Nuevo(@Valid @RequestBody CCitaDTOCreate frontInfo) {
    return Service.Nuevo(frontInfo);
  }

  @Operation(summary = "Borra una cita por medio de citaId")
  @DeleteMapping("/{citaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> EliminarCita(@PathVariable Long citaId) {
    boolean eliminado = Service.deleteById(citaId);

    if (eliminado) {
      return ResponseEntity.ok("La cita ha sido eliminada");
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada");
    }
  }
}

