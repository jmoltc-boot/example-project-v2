package cl.mobdev.rickandmorty.infraestructure.controller.exception;

import cl.mobdev.rickandmorty.api.model.ApiErrorResponse;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  @ExceptionHandler(GatewayException.class)
  public ResponseEntity<ApiErrorResponse> handleError(GatewayException ex) {
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.BAD_GATEWAY);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiErrorResponse> handleError(RuntimeException ex) {
    return new ResponseEntity<>(new ApiErrorResponse().message(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
