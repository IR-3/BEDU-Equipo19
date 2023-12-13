package org.bedu.java.backend.pet.exception;

public class TutorNotFoundException 
extends RuntimeException {
    public TutorNotFoundException(){
        super(
        "ERR_DATA_NOT_FOUND",
        "No existe el tutor",
        0 );
    }
    
}