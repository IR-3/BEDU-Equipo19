package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import org.bedu.java.backend.pet.service.CVeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Endpoint de veterinarios", description = "CRUD de veterinarios")
@RestController
@RequestMapping( "veterinarios" )
public class CVeterinarioController {

  @Autowired
  private CVeterinarioService  Service;

  @Operation(summary = "Obtiene la lista de todos los veterinarios")
  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CVeterinarioDTO>  RegresarLista() {
    return Service.RegresarLista();
  }

  @Operation(summary = "Crea un nuevo veterinario")
  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CVeterinarioDTO
  Nuevo( @Valid @RequestBody CVeterinarioDTOCreate frontInfo )
  throws CPersonaApellidoException, CPersonaContactoException {

    CPersonaDTOCreate persona = frontInfo.getClsPersona();
    persona.ValidarApellidos();
    persona.ValidarContacto();
    return Service.Nuevo( frontInfo );
  }

  @Operation(summary = "Borra un veterinario por medio de veterinarioId")
  @DeleteMapping("/{veterinarioId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> EliminarVeterinario(@PathVariable Long veterinadoId){
    boolean eliminado = Service.deleteById(veterinadoId);

    if(eliminado){
      return ResponseEntity.ok("El veterinario fue eliminado");
    }else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El veterinario no fue encontrado");
    }
  }
}
