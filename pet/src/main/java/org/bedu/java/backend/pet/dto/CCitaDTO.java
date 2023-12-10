package org.bedu.java.backend.pet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CCitaDTO {

  private long             lngCitaID;
  private LocalDate        clsDate;
  private LocalTime        clsTime;
  private String           strTratamiento;
  private CMascotaDTO      clsMascota;
  private CVeterinarioDTO  clsVeterinario;
}
