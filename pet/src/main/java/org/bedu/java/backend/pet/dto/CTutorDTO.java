package org.bedu.java.backend.pet.dto;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CTutorDTO {

  private long  lngTutorID;

  @Embedded
  private CPersonaDTO  clsTutor;
}
