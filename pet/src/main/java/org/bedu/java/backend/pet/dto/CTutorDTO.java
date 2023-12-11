package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CTutorDTO {
  
  @Schema(description = "Identificador de tutor.", example = "52")
  private long  lngTutorID;

  @Schema(description = "Persona, tutor de la mascota.")
  @Embedded
  private CPersonaDTO  clsTutor;
}
