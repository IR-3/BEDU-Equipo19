package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CMascotaDTO {

  @Schema( description = "Identificador de Mascota.", example = "20" )
  private long  lngMascotaID;

  @Schema( description = "Nombre de Mascota.", example = "Boni" )
  private String  strNombre;

  @Schema( description = "Especie de la Mascota.", example = "Conejo" )
  private String  strEspecie;

  @Schema( description = "Raza de la mascota.", example = "Rex" )
  private String  strRaza;

  @Schema( description = "Tutor de la mascota." )
  private CTutorDTO clsTutor;
}
