package org.bedu.java.backend.pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CCitaDTOCreate {

  @NotNull( message = "La fecha de la cita es obligatoria" )
  private LocalDate  clsDate;

  @NotNull( message = "La hora de la cita es obligatoria" )
  private LocalTime  clsTime;

  private String  strTratamiento;
  private long    lngMascotaID;
  private long    lngVetID;
}
