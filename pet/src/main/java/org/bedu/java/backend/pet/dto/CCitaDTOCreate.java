package org.bedu.java.backend.pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CCitaDTOCreate {

  @Schema( description = "Fecha de la cita.", example = "2024-01-22" )
  @NotNull( message = "La fecha de la cita es obligatoria" )
  private LocalDate  clsDate;

  @Schema( description = "Hora de la cita.", example = "14:30:00" )
  @NotNull( message = "La hora de la cita es obligatoria" )
  private LocalTime  clsTime;

  @Schema(description = "Tratamiento preescrito.", example = "Administrar 5 ml de antibiótico oral cada 12 horas durante 10 días.")
  private String    strTratamiento;

  @Schema(description = "Indentificador de Mascota.")
  private long      lngMascotaID;

  @Schema(description = "Identificador de Veterinario.")
  private long      lngVetID;
}
