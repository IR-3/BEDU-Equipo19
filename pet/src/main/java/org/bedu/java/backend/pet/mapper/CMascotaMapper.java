package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateMascotaDTO;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.model.CMascota;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface CMascotaMapper {

  // Convierte Modelo en DTO
  @Mapping( target = "clsTutor", source = "tutor" )
  CMascotaDTO EnDTO( CMascota mascota, CTutor tutor );

  // Convierte CreateDTO en Modelo
  @Mapping( target = "clsTutor", source = "tutor" )
  @Mapping( target = "lngMascotaID", ignore = true )
  CMascota EnModel
  ( CMascotaDTOCreate frontInfo, CTutor tutor );

  @Mapping(target = "lngMascotaID", ignore = true)
  void actualizarMascota(@MappingTarget CMascota model, UpdateMascotaDTO dto);
}
