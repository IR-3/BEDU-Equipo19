package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Embeddable
public class CPersonaDTOCreate {

  // El nombre de la persona es obligatorio
  @NotEmpty( message = "El nombre no puede estar vacio" )
  private String  strNombre;

  // Debe existir al menos un apellido
  private String  strPaterno;
  private String  strMaterno;

  // Debe existir al menos un medio de contacto
  private String  strEmail;
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
