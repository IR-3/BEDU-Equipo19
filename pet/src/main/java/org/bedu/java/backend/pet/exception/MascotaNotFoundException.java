package org.bedu.java.backend.pet.exception;

public class MascotaNotFoundException extends RuntimeException {
    
  public MascotaNotFoundException() {
  super(
    "ERR_DATA_NOT_FOUND",
    "No existe la mascota",
    0 );
  }    
}
