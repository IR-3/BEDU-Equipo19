package org.bedu.java.backend.pet.mapper;

import org.bedu.java.backend.pet.dto.CreateMascotaDTO;
import org.bedu.java.backend.pet.dto.MascotaDTO;
import org.bedu.java.backend.pet.model.Mascota;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


// MascotaMapper
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MascotaMapper {
    
    MascotaDTO toDTO(Mascota model);

    @Mapping(target = "mascotaId", ignore = true)
    Mascota toModel(CreateMascotaDTO dto);
    
}
