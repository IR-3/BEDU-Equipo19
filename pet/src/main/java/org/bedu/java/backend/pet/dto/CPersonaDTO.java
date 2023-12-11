package org.bedu.java.backend.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CPersonaDTO {

  @Schema(description = "Nombre de la persona.", example = "Sofia")
  private String  strNombre;

  @Schema(description = "Apellido paterno de la persona.", example = "Trejo")
  private String  strPaterno;

  @Schema(description = "Apellido materno de la persona.", example = "Miranda")
  private String  strMaterno;

  @Schema(description = "Correo de la persona.", example = "sofia56@gmail.com")
  private String  strEmail;

  @Schema(description = "Teléfono de la persona.", example = "5312453645")
  private String  strTelefono;
}
