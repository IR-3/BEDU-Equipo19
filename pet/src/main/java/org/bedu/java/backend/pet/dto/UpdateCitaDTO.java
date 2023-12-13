package org.bedu.java.backend.pet.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CVeterinario;

import lombok.Data;

@Data
public class UpdateCitaDTO {
  
  private LocalDate  clsDate;
  private LocalTime  clsTime;
  private String  strTratamiento;
  private CMascota  clsMascota;
  private CVeterinario  clsVeterinario;
    
}
