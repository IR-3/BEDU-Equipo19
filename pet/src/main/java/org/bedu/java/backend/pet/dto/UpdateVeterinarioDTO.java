package org.bedu.java.backend.pet.dto;

import org.bedu.java.backend.pet.model.CPersona;

import lombok.Data;

@Data
public class UpdateVeterinarioDTO {

    private CPersona clsPersona;

    private String strCedula;

    private String  strEspecialidad;

}
