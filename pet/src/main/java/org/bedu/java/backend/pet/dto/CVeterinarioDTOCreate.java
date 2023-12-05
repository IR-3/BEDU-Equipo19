package org.bedu.java.backend.pet.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CVeterinarioDTOCreate {

  // El nombre del veterinario es obligatorio
  @NotEmpty( message = "El nombre no puede estar vacio" )
  private String strNombre;

  // Debe existir al menos un apellido
  private String strPaterno;
  private String strMaterno;

  // La cedula es obligatoria
  @NotEmpty( message = "Debe ingresar la cedula profesional" )
  private String strCedula;

  private String strEspecialidad;

  public boolean ValidarApellidos() {
    return strPaterno != null || strMaterno != null;
  }
}
