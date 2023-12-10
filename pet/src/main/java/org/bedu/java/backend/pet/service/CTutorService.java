package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.mapper.CTutorMapper;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.repository.CTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTutorService {

  @Autowired
  private CTutorRepository  Repository;

  @Autowired
  private CTutorMapper  Mapper;

  // Regresa todos los tutores
  public List<CTutorDTO>  RegresarLista() {
    return Mapper.EnDTO( Repository.findAll() );
  }

  // Agrega un tutor a la base de datos
  public CTutorDTO Nuevo( CTutorDTOCreate frontInfo ) {
    CTutor tutor = Repository.save( Mapper.EnModelo( frontInfo ) );
    return Mapper.EnDTO( tutor );
  }

}
