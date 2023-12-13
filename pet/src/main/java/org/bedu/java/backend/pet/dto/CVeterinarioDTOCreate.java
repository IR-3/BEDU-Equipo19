package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CVeterinarioDTOCreate {

  @Schema(description = "Persona veterinario.")
  @Valid
  @NotNull( message = "Falta información de la persona" )
  @Embedded
  private CPersonaDTOCreate  clsPersona;

  @Schema(description = "Cédula profesional del veterinario.", example = "12345678")
  @NotEmpty( message = "La cedula profesionales obligatoria" )
  private String    strCedula;

  @Schema(description = "Especialidad del veterinario.", example = "Cardiología veterinaria")
  private String    strEspecialidad;
}
