package org.bedu.java.backend.pet.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuntimeException extends Exception {

  private final String  strCode;
  private final transient Object  objDetails;

  public RuntimeException
  ( String code, String message, Object details ) {
    super( message );
    strCode    = code;
    objDetails = details;
  }
}
