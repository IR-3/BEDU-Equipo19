package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.model.CCita;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CCitaMapper {
    //Modelo a DTO
    CCitaDTO toDto(CCita model);

    //DTO a Modelo
    @Mapping(target = "lngID", ignore = true)
    CCita toModel(CCitaDTOCreate dto);
}
