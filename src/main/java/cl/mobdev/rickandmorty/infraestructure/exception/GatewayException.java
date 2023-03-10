package cl.mobdev.rickandmorty.infraestructure.exception;

public class GatewayException extends RuntimeException {

  public GatewayException(String message) {
    super(message);
  }
}
