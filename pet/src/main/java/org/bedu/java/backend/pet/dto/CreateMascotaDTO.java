package org.bedu.java.backend.pet.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMascotaDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String categoria; // Tipo de animal

    @NotBlank
    private String raza;

    @NotBlank
    private String propietario;

    @NotNull
    private LocalDate fechaDeAlta;
    
}
