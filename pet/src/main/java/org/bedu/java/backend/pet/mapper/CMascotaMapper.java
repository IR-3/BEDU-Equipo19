package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.model.CMascota;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CMascotaMapper {

  // Convierte Modelo en DTO
  @Mapping( target = "clsTutor", source = "tutor" )
  CMascotaDTO EnDTO( CMascota mascota, CTutor tutor );

  // Convierte CreateDTO en Modelo
  @Mapping( target = "clsTutor", source = "tutor" )
  @Mapping( target = "lngMascotaID", ignore = true )
  CMascota EnModel
  ( CMascotaDTOCreate frontInfo, CTutor tutor );
}
