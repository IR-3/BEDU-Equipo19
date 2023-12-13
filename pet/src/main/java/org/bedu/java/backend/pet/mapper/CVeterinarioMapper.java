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
  uses = CPersonaMapper.class,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  
public interface CVeterinarioMapper {

  // Convierte Modelo en DTO
  CVeterinarioDTO EnDTO(CVeterinario model);

  // Convierte una lista Model en lista DTO
  List<CVeterinarioDTO> EnDTO(List<CVeterinario> model);

  // Convierte CreateDTO en Modelo
  @Mapping(target = "lngVetID", ignore = true)
  @Mapping(target = "clsPersona", source = "frontInfo.clsPersona") // Asegurar el mapeo del campo clsPersona
  CVeterinario EnModel(CVeterinarioDTOCreate frontInfo);

  @Mapping(target = "lngVetID", ignore = true)
  void actualizarVeterinario(@MappingTarget CVeterinario model, UpdateVeterinarioDTO dto);

}
