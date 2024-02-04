package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CTutorDTOCreate {

  @Schema( description = "Persona, tutor de la mascota." )
  @Valid
  @NotNull( message = "Falta información de la persona" )
  @Embedded
  private CPersonaDTOCreate  clsTutor;
}
