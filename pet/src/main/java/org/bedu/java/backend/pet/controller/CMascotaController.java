package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.exception.CMascotaTutorException;
import org.bedu.java.backend.pet.service.CMascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping( "mascotas" )
public class CMascotaController {

  @Autowired
  private CMascotaService  Service;


  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CMascotaDTO> RegresarLista() {
    return Service.RegresarLista();
  }


  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CMascotaDTO Nuevo
  ( @Valid @RequestBody CMascotaDTOCreate frontInfo )
  throws CMascotaTutorException {
    return Service.Nuevo( frontInfo );
  }

  @DeleteMapping("/{mascotaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> eliminarMascota(@PathVariable Long mascotaId){
    boolean eliminado = Service.deleteById(mascotaId);

    if(eliminado){
      return ResponseEntity.ok("La mascota ha sido eliminada");
    }else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Mascota no encontrada");
    }
  }

}
