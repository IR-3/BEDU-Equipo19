package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.mapper.CVeterinarioMapper;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CVeterinarioService {

  @Autowired
  CVeterinarioRepository Repository;

  @Autowired
  CVeterinarioMapper Mapper;

  // Regresa una lista con todos los veterinarios
  public List<CVeterinarioDTO> RegresarLista() {
    return Repository.findAll().stream()
            .map( Mapper::EnDTO ).toList();
  }

  // Agrega un veterinario
  public CVeterinarioDTO Nuevo( CVeterinarioDTOCreate frontInfo ) {

    // No debe existir un veterinario con la misma cedula profesional
    // Al menos uno de los apellidos debe existir
    if( BuscarCedula( frontInfo.getStrCedula() ) != 0
            || frontInfo.ValidarApellidos() == false )
      return null;

    // Agrega el elemento
    CVeterinario nuevo = null;
    nuevo = Repository.save( Mapper.EnModelo( frontInfo ) );
    return Mapper.EnDTO( nuevo );
  }

  // Busca cedula profesional; regresa el Id del veterinario
  public Long BuscarCedula( String cedula ) {

    CVeterinario registro = Repository.findAll().stream()
            .filter( item -> cedula.equals( item.getStrCedula() ) )
            .findAny().orElse( null );

    return registro == null ? 0 : registro.getLngID();
  }
}
