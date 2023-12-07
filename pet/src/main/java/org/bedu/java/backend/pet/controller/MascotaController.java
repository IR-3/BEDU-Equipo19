package org.bedu.java.backend.pet.controller;

import jakarta.validation.Valid;

import org.bedu.java.backend.pet.dto.CreateMascotaDTO;
import org.bedu.java.backend.pet.dto.MascotaDTO;
import org.bedu.java.backend.pet.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mascota")
public class MascotaController {

    @Autowired
    private MascotaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MascotaDTO> findAll(){
        return service.findAll();
    }

    // Crea una mascota
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MascotaDTO save(@Valid @RequestBody CreateMascotaDTO data){
        return service.save(data);
    }
}
