package org.bedu.java.backend.pet.controller;

import jakarta.validation.Valid;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.service.CVeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "veterinario" )
public class CVeterinarioController {

  @Autowired
  CVeterinarioService Service;

  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CVeterinarioDTO> RegresarLista() {
    return Service.RegresarLista();
  }

  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CVeterinarioDTO
  Nuevo( @Valid @RequestBody CVeterinarioDTOCreate frontInfo ) {
    return Service.Nuevo( frontInfo );
  }
}
