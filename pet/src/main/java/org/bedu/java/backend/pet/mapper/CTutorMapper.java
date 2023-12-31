package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.dto.CTutorDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateTutorDTO;
import org.bedu.java.backend.pet.model.CTutor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  uses = CTutorMapper.class, 
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )

public interface CTutorMapper {

  // Convierte Modelo en DTO
  CTutorDTO EnDTO( CTutor tutor );

  // Convierte una lista Model en lista DTO
  List<CTutorDTO> EnDTO( List<CTutor> model );

  // Convierte CreateDTO en Modelo
  @Mapping( target = "lngTutorID", ignore = true )
  @Mapping(target = "clsTutor", source = "frontInfo.clsTutor")
  CTutor EnModelo( CTutorDTOCreate frontInfo );


  @Mapping(target = "lngTutorID", ignore = true)
  void actualizarTutor(@MappingTarget CTutor model, UpdateTutorDTO dto);
}
