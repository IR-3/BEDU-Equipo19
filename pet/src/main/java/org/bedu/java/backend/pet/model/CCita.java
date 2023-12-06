package org.bedu.java.backend.pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CCita")
public class CCita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lngID;
    @Column(name = "Fecha", length = 150, nullable = false)
    private String strFecha;
    @Column(name = "hora", nullable = false)
    private String strHora;
    @Column(name = "Comentario")
    private  String strComentario;

}
