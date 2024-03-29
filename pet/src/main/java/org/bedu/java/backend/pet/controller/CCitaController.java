package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.dto.CCitaFindByFechaHora;
import org.bedu.java.backend.pet.dto.UpdateCitaDTO;
import org.bedu.java.backend.pet.exception.CitaExistenteException;
import org.bedu.java.backend.pet.exception.CitaNotFoundException;
import org.bedu.java.backend.pet.exception.MascotaNotFoundException;
import org.bedu.java.backend.pet.exception.VeterinarioNotFoundException;
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

@Tag( name = "Endpoint de Citas", description = "CRUD de citas" )
@RestController
@RequestMapping( "citas" )
public class CCitaController {

  private CCitaService clsService;

  @Autowired
  public CCitaController( CCitaService service ) {
    clsService = service;
  }

  @Operation( summary = "Obtiene la lista de todos las citas" )
  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CCitaDTO> regresarLista() {
    return clsService.regresarLista();
  }

  @Operation( summary = "Crea una nueva cita" )
  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CCitaDTO nuevo( @Valid @RequestBody CCitaDTOCreate frontInfo )
  throws MascotaNotFoundException, VeterinarioNotFoundException, CitaExistenteException {
    return clsService.nuevo( frontInfo );
  }

  @Operation( summary = "Borra una cita por medio de citaId" )
  @DeleteMapping( "/{citaId}" )
  @ResponseStatus( HttpStatus.NO_CONTENT )
  public ResponseEntity<String> eliminarCita( @PathVariable Long citaId ) {
    boolean eliminado = clsService.deleteById( citaId );

    if ( eliminado ) {
      return ResponseEntity.ok( "La cita ha sido eliminada" );
    } else {
      throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Cita no encontrada" );
    }
  }

  @Operation( summary = "Actualiza una cita por medio de citaId" )
  @PutMapping( "/{citaId}" )
  @ResponseStatus( HttpStatus.NO_CONTENT )
  public void update( @PathVariable Long citaId, @Valid @RequestBody UpdateCitaDTO data )
  throws CitaNotFoundException {
    clsService.actualizarCita( citaId, data );
  }

  @Operation( summary = "Buscar Cita por fecha y hora" )
  @GetMapping( "/buscarPorFecha" )
  @ResponseStatus( HttpStatus.OK )
  public ResponseEntity<CCitaDTO> findByFechaHora( @Valid @RequestBody CCitaFindByFechaHora data ) {
    try {
      CCitaDTO citaDTO = clsService.findByDateHour( data );
      return ResponseEntity.ok( citaDTO );
    } catch( CitaNotFoundException e ) {
      return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
    }
  }
}

