package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;

@Getter
@Setter
@ToString
@Entity
@Table( name = "veterinario" )
@Check( constraints = "paterno IS NOT NULL OR materno IS NOT NULL" )
public class CVeterinario {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private long   lngID;

  // El nombre es obligatorio
  @Column( name = "nombre", nullable = false, length = 40 )
  private String strNombre;

  // Debe haber apellido paterno o materno
  @Column( name = "paterno", length = 24 )
  private String strPaterno;

  @Column( name = "materno", length = 24 )
  private String strMaterno;

  // El veterinario debe tener cedula profesional
  @Column( name = "cedula", nullable = false, length = 16 )
  private String strCedula;

  // Un veterinario puede no tener especialidad
  @Column( name = "especialidad", length = 48 )
  private String strEspecialidad;
}
