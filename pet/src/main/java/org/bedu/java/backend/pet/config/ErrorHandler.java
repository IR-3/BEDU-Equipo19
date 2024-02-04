package org.bedu.java.backend.pet.config;

import org.bedu.java.backend.pet.dto.ErrorDTO;
import org.bedu.java.backend.pet.exception.RuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler( MethodArgumentNotValidException.class )
  public ErrorDTO
  validationError( MethodArgumentNotValidException exception ) {
    List<FieldError> fieldErrors;
    fieldErrors = exception.getBindingResult().getFieldErrors();
    List<String> errors = fieldErrors.stream()
                          .map( x -> x.getDefaultMessage() )
                          .toList();

    return new ErrorDTO(
      "ERR_VALID",
      "Hubo un error al procesar los datos de entrada",
       errors );
  }


  @ExceptionHandler( RuntimeException.class )
  public ErrorDTO applicationError( RuntimeException exception ) {
    return new ErrorDTO(
      exception.getStrCode(),
      exception.getMessage(),
      exception.getObjDetails() );
  }

  @ExceptionHandler( Exception.class )
  public ErrorDTO unknownError( Exception exception ) {
    log.error( exception.getMessage() );
    return new ErrorDTO(
      "ERR_UNKNOWN",
      "Ocurrió un error desconocido",
      null );
  }
}