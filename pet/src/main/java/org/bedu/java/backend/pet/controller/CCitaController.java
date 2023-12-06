package org.bedu.java.backend.pet.controller;

import jakarta.validation.Valid;
import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.service.CCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cita")
public class CCitaController {

    @Autowired
    private CCitaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CCitaDTO>findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CCitaDTO save(@Valid @RequestBody CCitaDTOCreate data){
        return service.save(data);
    }
}
