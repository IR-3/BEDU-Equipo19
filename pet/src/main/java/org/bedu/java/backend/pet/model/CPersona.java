package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CPersona {

  private String  strNombre;
  private String  strPaterno;
  private String  strMaterno;
  private String  strEmail;
  private String  strTelefono;
}
