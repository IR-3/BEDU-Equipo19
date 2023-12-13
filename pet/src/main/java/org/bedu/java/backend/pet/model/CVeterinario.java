package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "veterinarios" )
public class CVeterinario {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @MapsId( "lngVetID" )
  private long  lngVetID;

  @Embedded
  @Column( name = "tutor", nullable = false )
  private CPersona  clsPersona;

  // El veterinario debe tener cedula profesional
  @Column(
    name = "cedula", nullable = false,
    length = 16, unique = true )
  private String  strCedula;

  // Un veterinario puede no tener especialidad
  @Column( name = "especialidad", length = 48 )
  private String  strEspecialidad;
}
