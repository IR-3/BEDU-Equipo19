package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.model.CPersona;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CVeterinarioDTOCreate {

  @Valid
  @NotNull( message = "Falta información de la persona" )
  @Embedded
  private CPersonaDTOCreate  clsPersona;

  @NotEmpty( message = "La cedula profesionales obligatoria" )
  private String    strCedula;
  private String    strEspecialidad;
}
