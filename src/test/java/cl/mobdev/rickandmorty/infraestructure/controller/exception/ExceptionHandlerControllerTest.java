package cl.mobdev.rickandmorty.infraestructure.controller.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.api.model.ApiErrorResponse;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionHandlerControllerTest {

  private ExceptionHandlerController controller;

  @BeforeEach
  void setUp() {
    this.controller = new ExceptionHandlerController();
  }

  @Test
  void should_return_bad_gateway_status() {
    String messageExpected = "some error";
    HttpStatus statusExpected = HttpStatus.BAD_GATEWAY;

    //GIVEN
    GatewayException gatewayException = new GatewayException("some error");

    //WHEN
    ResponseEntity<ApiErrorResponse> response = this.controller.handleError(gatewayException);

    //THEN
    assertEquals(statusExpected, response.getStatusCode());
    assertEquals(messageExpected, response.getBody().getMessage());
  }

  @Test
  void should_return_internal_server_error_status() {
    String messageExpected = "some error";
    HttpStatus statusExpected = HttpStatus.INTERNAL_SERVER_ERROR;

    //GIVEN
    RuntimeException runtimeException = new RuntimeException("some error");

    //WHEN
    ResponseEntity<ApiErrorResponse> response = this.controller.handleError(runtimeException);

    //THEN
    assertEquals(statusExpected, response.getStatusCode());
    assertEquals(messageExpected, response.getBody().getMessage());
  }

}