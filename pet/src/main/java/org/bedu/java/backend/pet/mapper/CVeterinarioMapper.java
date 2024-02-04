package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateVeterinarioDTO;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper( 
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
  
public interface CVeterinarioMapper {

  // Convierte Modelo en DTO
  CVeterinarioDTO enDTO( CVeterinario model );

  // Convierte una lista Model en lista DTO
  List<CVeterinarioDTO> enDTO( List<CVeterinario> model );

  // Convierte CreateDTO en Modelo
  @Mapping( target = "lngVetID", ignore = true )
  @Mapping( target = "clsPersona", source = "frontInfo.clsPersona" ) // Asegurar el mapeo del campo clsPersona
  CVeterinario enModel( CVeterinarioDTOCreate frontInfo );

  @Mapping( target = "lngVetID", ignore = true )
  void actualizarVeterinario( @MappingTarget CVeterinario veterinario, UpdateVeterinarioDTO dto );

}
