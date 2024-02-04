package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.model.CTutor;

import lombok.Data;

@Data
public class UpdateMascotaDTO {

  private long  lngMascotaID;
  private String  strNombre;
  private String  strEspecie;
  private String  strRaza;
  private CTutor  clsTutor;    
}
