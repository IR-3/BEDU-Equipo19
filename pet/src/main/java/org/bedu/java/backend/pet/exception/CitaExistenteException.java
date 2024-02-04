package org.bedu.java.backend.pet.exception;

public class CitaExistenteException extends RuntimeException {

  public CitaExistenteException() {
    super(
    "ERR_DATA_NOT_FOUND",
    "Ya existe una cita en la misma fecha y hora",
    0 );
  }
}
