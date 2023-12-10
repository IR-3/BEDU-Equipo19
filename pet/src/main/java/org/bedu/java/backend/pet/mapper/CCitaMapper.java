package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.model.CCita;
import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface CCitaMapper {

  // Convierte Modelo en DTO
  @Mapping( target = "clsMascota", source = "mascota" )
  @Mapping( target = "clsVeterinario", source = "veterinario" )
  CCitaDTO EnDTO
  ( CCita cita, CMascota mascota, CVeterinario veterinario );

  // Convierte CreateDTO en Modelo
  @Mapping( target = "clsMascota", source = "mascota" )
  @Mapping( target = "clsVeterinario", source = "veterinario" )
  @Mapping( target = "lngCitaID", ignore = true )
  CCita EnModel(
    CCitaDTOCreate frontInfo,
    CMascota mascota,
    CVeterinario veterinario );
}
