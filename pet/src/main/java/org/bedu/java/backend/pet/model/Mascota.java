package org.bedu.java.backend.pet.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Mascota

@Getter
@Setter
@ToString
@Entity
@Table(name = "mascota")
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mascotaId;

    @Column(nullable = false, length =  100)
    private String name;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String raza;

    @Column(nullable = false)
    private String propietario;

    @Column(nullable = false)
    private LocalDate fechaDeAlta;

}
