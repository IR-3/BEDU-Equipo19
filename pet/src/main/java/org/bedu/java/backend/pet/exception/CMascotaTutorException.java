package org.bedu.java.backend.pet.exception;

public class CMascotaTutorException extends RuntimeException {

  public CMascotaTutorException() {
    super(
    "ERR_DATA_NOT_FOUND",
    "Él identificador del tutor no existe",
    0 );
  }
}
