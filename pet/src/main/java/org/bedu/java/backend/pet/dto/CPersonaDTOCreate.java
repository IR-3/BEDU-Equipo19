package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Embeddable
public class CPersonaDTOCreate {

  // El nombre de la persona es obligatorio
  @Schema(description = "Nombre de la persona.", example = "Sofia")
  @NotEmpty( message = "El nombre no puede estar vacio" )
  private String  strNombre;

  // Debe existir al menos un apellido
  @Schema(description = "Apellido paterno de la persona.", example = "Trejo")
  private String  strPaterno;

  @Schema(description = "Apellido materno de la persona.", example = "Miranda")
  private String  strMaterno;

  // Debe existir al menos un medio de contacto
  @Schema(description = "Correo de la persona.", example = "sofia56@gmail.com")
  private String  strEmail;

  @Schema(description = "Teléfono de la persona.", example = "5312453645")
  private String  strTelefono;

  // Validar apellidos
  public boolean ValidarApellidos()
  throws CPersonaApellidoException {
    if( strPaterno == null && strMaterno == null )
      throw new CPersonaApellidoException();
    return true;
  }

  // Validar medios de contacto
  public boolean ValidarContacto()
  throws CPersonaContactoException {
    if( strEmail == null && strTelefono == null )
      throw new CPersonaContactoException();
    return true;
  }

}
