package org.bedu.java.backend.pet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CCitaDTOCreate {
    @NotBlank
    private String strFecha;
    @NotBlank
    private String strHora;
    private String strComentario;
}
