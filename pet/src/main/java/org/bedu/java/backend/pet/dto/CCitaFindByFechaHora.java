package org.bedu.java.backend.pet.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CCitaFindByFechaHora {

  @Schema( description = "Fecha de la cita.", example = "2024-01-22" )
  @NotNull( message = "La fecha de la cita es obligatoria" )
  private LocalDate clsDate;

  @Schema( description = "Hora de la cita.", example = "14:30:00" )
  @NotNull( message = "La hora de la cita es obligatoria" )
  private LocalTime clsTime;
}
