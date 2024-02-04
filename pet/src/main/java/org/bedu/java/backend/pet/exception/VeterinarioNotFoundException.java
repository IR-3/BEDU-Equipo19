package org.bedu.java.backend.pet.exception;

public class VeterinarioNotFoundException extends RuntimeException {

  public VeterinarioNotFoundException() {
    super(
      "ERR_DATA_NOT_FOUND",
      "No existe el veterinario",
      0 );
  }
}
