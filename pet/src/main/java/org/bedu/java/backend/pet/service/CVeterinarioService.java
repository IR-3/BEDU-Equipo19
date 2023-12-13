package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateVeterinarioDTO;
import org.bedu.java.backend.pet.mapper.CVeterinarioMapper;
import org.bedu.java.backend.pet.model.CPersona;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bedu.java.backend.pet.exception.VeterinarioNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CVeterinarioService {

  @Autowired
  private CVeterinarioRepository  Repository;

  @Autowired
  private CVeterinarioMapper  Mapper;

  // Regresa todos los veterinarios
  public List<CVeterinarioDTO> RegresarLista() {
    return Mapper.EnDTO( Repository.findAll() );
  }

  // Agrega un veterinario a la base de datos
  public CVeterinarioDTO Nuevo( CVeterinarioDTOCreate frontInfo ) {
    CVeterinario veterinario;
    veterinario = Repository.save( Mapper.EnModel( frontInfo ) );
    return Mapper.EnDTO( veterinario );
  }

  public boolean deleteById(Long veterinadoId){
    Optional<CVeterinario>veterinarioOptional=Repository.findById(veterinadoId);

    if(veterinarioOptional.isPresent()){
      Repository.delete(veterinarioOptional.get());
      return true;
    }else{
      return false;
    }
  }

  public void actualizarVeterinario(Long veterinarioId, UpdateVeterinarioDTO dto) throws VeterinarioNotFoundException {
    Optional<CVeterinario> result = Repository.findById(veterinarioId);
    if (result.isEmpty()) {
        throw new VeterinarioNotFoundException();
    }

    CVeterinario veterinario = result.get();

    // Actualizar clsPersona solo si se proporciona en el DTO
    if (dto.getClsPersona() != null) {
        CPersona personaDTO = dto.getClsPersona();
        CPersona persona = veterinario.getClsPersona();

        // Actualizar campos individuales de clsPersona si se proporcionan
        if (personaDTO.getStrNombre() != null) {
            persona.setStrNombre(personaDTO.getStrNombre());
        }
        if (personaDTO.getStrPaterno() != null) {
            persona.setStrPaterno(personaDTO.getStrPaterno());
        }
        if (personaDTO.getStrMaterno() != null) {
            persona.setStrMaterno(personaDTO.getStrMaterno());
        }
        if (personaDTO.getStrEmail() != null) {
            persona.setStrEmail(personaDTO.getStrEmail());
        }
        if (personaDTO.getStrTelefono() != null) {
            persona.setStrTelefono(personaDTO.getStrTelefono());
        }
    }

    // Actualizar strCedula si se proporciona en el DTO
    if (dto.getStrCedula() != null) {
        veterinario.setStrCedula(dto.getStrCedula());
    }

    // Actualizar strEspecialidad si se proporciona en el DTO
    if (dto.getStrEspecialidad() != null) {
        veterinario.setStrEspecialidad(dto.getStrEspecialidad());
    }

    Repository.save(veterinario);
  }

}
