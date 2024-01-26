package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table( name = "citas" )
public class CCita {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private long  lngCitaID;

  @Column( name = "fecha", nullable = false )
  private LocalDate  clsDate;

  @Column( name = "hora_inicio", nullable = false )
  private LocalTime  clsTime;

  @Column(name = "duracion", nullable = false)
  private int intMinutos;

  @Column(name = "hora_finalizacion")
  private LocalTime clsFin;

  @Column( name = "tratamiento" )
  private String  strTratamiento;

  @ManyToOne
  private CMascota  clsMascota;

  @ManyToOne
  private CVeterinario  clsVeterinario;
}
