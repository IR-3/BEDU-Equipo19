package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.model.CPersona;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CPersonaMapper {

  // Convierte Modelo en DTO
  CPersonaDTO EnDTO( CPersona persona );

  // Convierte CPersonaDTOCreate en modelo
  CPersona EnModelo( CPersonaDTOCreate personaDTO );
}
