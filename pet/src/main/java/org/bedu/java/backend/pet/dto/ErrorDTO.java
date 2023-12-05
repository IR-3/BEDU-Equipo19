package org.bedu.java.backend.pet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDTO {

  public String strCode;
  public String strMessage;
  public Object objDetails;

  public ErrorDTO( String code, String message, Object details ) {
    strCode    = code;
    strMessage = message;
    objDetails = details;
  }

}
