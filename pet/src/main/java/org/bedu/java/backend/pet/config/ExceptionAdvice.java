package org.bedu.java.backend.pet.config;

import org.bedu.java.backend.pet.dto.ErrorDTO;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler( MethodArgumentNotValidException.class )
  public ErrorDTO
  validateErrors( MethodArgumentNotValidException exception ) {

    List<String>     errors      = new LinkedList<>();
    List<FieldError> fieldErrors =
      exception.getBindingResult().getFieldErrors();

    for( FieldError fieldError : fieldErrors ) {
      errors.add( fieldError.getDefaultMessage() );
    }

    return new ErrorDTO(
            "ERR_VALID",
            "Error al validar datos de entrada",
            errors );
  }
}
