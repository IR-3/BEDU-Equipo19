package org.bedu.java.backend.pet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CCitaDTO {

  @Schema(description = "Identificador de cita.", example = "10")
  private long              lngCitaID;

  @Schema(description = "Fecha de la cita.", example = "2024-01-22")
  private LocalDate         clsDate;

  @Schema(description = "Hora de la cita.", example = "14:30:00")
  private LocalTime         clsTime;

  @Schema(description = "Tratamiento preescrito.", example = "Administrar 5 ml de antibiótico oral cada 12 horas durante 10 días.")
  private String            strTratamiento;

  @Schema(description = "Mascota que será atendida.")
  private CMascotaDTO       clsMascota;

  @Schema(description = "Veterinario que brindará la atención durante la cita médica.")
  private CVeterinarioDTO   clsVeterinario;
}
