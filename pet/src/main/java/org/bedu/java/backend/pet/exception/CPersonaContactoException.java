package org.bedu.java.backend.pet.exception;

public class CPersonaContactoException
extends RuntimeException {

  public CPersonaContactoException() {
    super(
      "ERR_DATA_NOT_FOUND",
      "Debe haber al menos un medio de contacto",
      0  );
  }
}
