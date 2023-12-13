package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateTutorDTO;
import org.bedu.java.backend.pet.exception.TutorNotFoundException;
import org.bedu.java.backend.pet.mapper.CTutorMapper;
import org.bedu.java.backend.pet.model.CPersona;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.repository.CTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
  @Transactional
  public CTutorDTO Nuevo( CTutorDTOCreate frontInfo ) {
    CTutor tutor = Repository.save( Mapper.EnModelo( frontInfo ) );
    return Mapper.EnDTO( tutor );
  }

  @Transactional
  public boolean deleteById(Long tutorId){
    Optional<CTutor>tutorOptional=Repository.findById(tutorId);

    if(tutorOptional.isPresent()){
      Repository.delete(tutorOptional.get());
      return true;
    }else{
      return false;
    }
  }

  @Transactional
  public void actualizarTutor(Long tutorId, UpdateTutorDTO data) throws TutorNotFoundException {
        Optional<CTutor> result = Repository.findById(tutorId);
        if (result.isEmpty()) {
            throw new TutorNotFoundException();
        }

        CTutor tutor = result.get();

        // Actualizar clsTutor solo si se proporciona en el DTO
        if (data.getClsTutor() != null) {
            CPersona tutorDTO = data.getClsTutor();
            CPersona tutorPersona = tutor.getClsTutor();

            // Actualizar campos individuales de clsTutor si se proporcionan
            if (tutorDTO.getStrNombre() != null) {
                tutorPersona.setStrNombre(tutorDTO.getStrNombre());
            }
            if (tutorDTO.getStrPaterno() != null) {
                tutorPersona.setStrPaterno(tutorDTO.getStrPaterno());
            }
            if (tutorDTO.getStrMaterno() != null) {
                tutorPersona.setStrMaterno(tutorDTO.getStrMaterno());
            }
            if (tutorDTO.getStrEmail() != null) {
                tutorPersona.setStrEmail(tutorDTO.getStrEmail());
            }
            if (tutorDTO.getStrTelefono() != null) {
                tutorPersona.setStrTelefono(tutorDTO.getStrTelefono());
            }
        }

        Repository.save(tutor);
    }

}
