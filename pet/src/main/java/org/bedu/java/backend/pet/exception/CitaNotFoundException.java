package org.bedu.java.backend.pet.exception;

public class CitaNotFoundException 
extends RuntimeException{
    public CitaNotFoundException(){
        super(
      "ERR_DATA_NOT_FOUND",
      "Él identificador de la cita no existe",
      0 );
    }
    
}
