package org.bedu.java.backend.pet.service;

import java.util.List;

import org.bedu.java.backend.pet.dto.CreateMascotaDTO;
import org.bedu.java.backend.pet.dto.MascotaDTO;
import org.bedu.java.backend.pet.mapper.MascotaMapper;
import org.bedu.java.backend.pet.model.Mascota;
import org.bedu.java.backend.pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// MascotaService

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repository;

    @Autowired
    private MascotaMapper mapper;

    public List<MascotaDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public MascotaDTO save(CreateMascotaDTO data){
        Mascota entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

}
