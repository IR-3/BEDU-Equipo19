package org.bedu.java.backend.pet.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CMascotaDTOCreate {

  @NotEmpty( message = "El nombre de la mascota es obligatorio" )
  private String  strNombre;

  @NotEmpty( message = "La especie de la mascota es obligatoria" )
  private String  strEspecie;
  private String  strRaza;
  private long    lngTutorID;
}
