package org.bedu.java.backend.pet.dto;

import lombok.Data;

@Data
public class CMascotaDTO {

  private long       lngMascotaID;
  private String     strNombre;
  private String     strEspecie;
  private String     strRaza;
  private CTutorDTO  clsTutor;
}
