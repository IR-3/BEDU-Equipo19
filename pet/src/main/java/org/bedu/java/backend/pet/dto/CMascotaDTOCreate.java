package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CMascotaDTOCreate {

  @Schema(description = "Nombre de Mascota.", example = "Boni")
  @NotEmpty( message = "El nombre de la mascota es obligatorio" )
  private String  strNombre;

  @Schema(description = "Especie de la Mascota.", example = "Conejo")
  @NotEmpty( message = "La especie de la mascota es obligatoria" )
  private String  strEspecie;

  @Schema(description = "Raza de la mascota.", example = "Rex")
  private String  strRaza;

  @Schema(description = "Tutor de la mascota.")
  private long    lngTutorID;
}
