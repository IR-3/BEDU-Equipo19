package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "mascotas" )
public class CMascota {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @MapsId( "lngMascotaID" )
  private long  lngMascotaID;

  @Column( name = "nombre", nullable = false, length = 32 )
  private String  strNombre;

  @Column( name = "especie", nullable = false, length = 32 )
  private String  strEspecie;

  @Column( name = "raza", length = 32 )
  private String  strRaza;

  @ManyToOne
  private CTutor  clsTutor;
}
