package org.bedu.java.backend.pet.exception;

public class CPersonaApellidoException extends RuntimeException {

  public CPersonaApellidoException() {
    super(
      "ERR_DATA_NOT_FOUND",
      "Debe haber al menos un apellido",
      0 );
  }
}
