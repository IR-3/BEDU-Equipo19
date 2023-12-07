package org.bedu.java.backend.pet.dto;

import java.time.LocalDate;
import lombok.Data;

// MascotaDTO
@Data
public class MascotaDTO {
    
    private long mascotaId;
    private String name;
    private String categoria;
    private String raza;
    private String propietario;
    private LocalDate fechaDeAlta;
    
}
