package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.mapper.CCitaMapper;
import org.bedu.java.backend.pet.model.CCita;
import org.bedu.java.backend.pet.repository.CCitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CCitaService {

    @Autowired
    private CCitaRepository repository;

    @Autowired
    private CCitaMapper mapper;

    public List<CCitaDTO>findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public CCitaDTO save(CCitaDTOCreate data){
        CCita entity = repository.save(mapper.toModel(data));
        return mapper.toDto(entity);
    }
}
