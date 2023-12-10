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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "veterinarios" )
public class CVeterinarioController {

  @Autowired
  private CVeterinarioService  Service;

  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CVeterinarioDTO>  RegresarLista() {
    return Service.RegresarLista();
  }

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
}
