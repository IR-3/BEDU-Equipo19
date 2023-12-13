package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateTutorDTO;
import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import org.bedu.java.backend.pet.exception.TutorNotFoundException;
import org.bedu.java.backend.pet.service.CTutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Endpoint de Tutores", description = "CRUD de tutores")
@RestController
@RequestMapping( "tutores" )
public class CTutorController {

  @Autowired
  private CTutorService Service;

  @Operation(summary = "Obtiene la lista de todos los tutores de las mascotas")
  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CTutorDTO> RegresarLista() {
    return Service.RegresarLista();
  }

  @Operation(summary = "Crea un nuevo tutor")
  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CTutorDTO Nuevo
  ( @Valid @RequestBody CTutorDTOCreate frontInfo )
  throws CPersonaApellidoException, CPersonaContactoException {

    CPersonaDTOCreate persona = frontInfo.getClsTutor();
    persona.ValidarApellidos();
    persona.ValidarContacto();
    return Service.Nuevo( frontInfo );
  }

  @Operation(summary = "Borra un tutor por medio de tutorId")
  @DeleteMapping("/{tutorId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String>EliminarTutor(@PathVariable Long tutorId){
    boolean eliminado = Service.deleteById(tutorId);

    if(eliminado){
      return ResponseEntity.ok("El tutor ha sido eliminado");
    }else{
      throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor no encontrado");
    }
  }

  @PutMapping("/{tutorId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Long tutorId, @Valid @RequestBody UpdateTutorDTO data) throws TutorNotFoundException {
    Service.actualizarTutor(tutorId, data);
  }
}
