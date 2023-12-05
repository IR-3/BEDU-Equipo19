package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CVeterinarioMapper {

  // Convierte Modelo en DTO
  CVeterinarioDTO EnDTO( CVeterinario veterinario );

  // Convierte DTOCreate en Modelo
  @Mapping( target = "lngID", ignore = true )
  CVeterinario EnModelo( CVeterinarioDTOCreate frontEnd );
}
