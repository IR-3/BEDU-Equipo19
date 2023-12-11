package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.model.CPersona;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CVeterinarioDTO {

  @Schema(description = "Identificador de veterinario.", example = "32")
  private long      lngVetID;

  @Schema(description = "Persona veterinario.")
  @Embedded
  private CPersona  clsPersona;

  @Schema(description = "Cédula profesional del veterinario.", example = "12345678")
  private String    strCedula;

  @Schema(description = "Especialidad del veterinario.", example = "Cardiología veterinaria")
  private String    strEspecialidad;
}
