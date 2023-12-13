package org.bedu.java.backend.pet.controller;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateMascotaDTO;
import org.bedu.java.backend.pet.exception.CMascotaTutorException;
import org.bedu.java.backend.pet.exception.MascotaNotFoundException;
import org.bedu.java.backend.pet.service.CMascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Endpoint de Mascotas", description = "CRUD de mascotas")
@RestController
@RequestMapping( "mascotas" )
public class CMascotaController {

  @Autowired
  private CMascotaService  Service;

  @Operation(summary = "Obtiene la lista de todos las mascotas")
  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<CMascotaDTO> RegresarLista() {
    return Service.RegresarLista();
  }

  @Operation(summary = "Crea una nueva mascota")
  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public CMascotaDTO Nuevo
  ( @Valid @RequestBody CMascotaDTOCreate frontInfo )
  throws CMascotaTutorException {
    return Service.Nuevo( frontInfo );
  }

  @Operation(summary = "Borra una mascota por medio de mascotaId")
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

  @PutMapping("/{mascotaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Long mascotaId, @Valid @RequestBody UpdateMascotaDTO data) throws MascotaNotFoundException {
    Service.actualizarMascota(mascotaId, data);
  }

}
