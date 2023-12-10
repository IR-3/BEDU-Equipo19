package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "tutores" )
public class CTutor {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @MapsId( "lngTutorID" )
  private long  lngTutorID;

  @Embedded
  @Column( name = "tutor", nullable = false )
  private CPersona  clsTutor;
}
