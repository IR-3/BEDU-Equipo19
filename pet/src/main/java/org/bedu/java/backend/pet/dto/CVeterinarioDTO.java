package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.model.CPersona;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class CVeterinarioDTO {

  private long      lngVetID;

  @Embedded
  private CPersona  clsPersona;
  private String    strCedula;
  private String    strEspecialidad;
}
