package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CTutorDTOCreate {

  @Valid
  @NotNull( message = "Falta información de la persona" )
  @Embedded
  private CPersonaDTOCreate  clsTutor;
}