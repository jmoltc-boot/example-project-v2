package cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.model;

import java.util.Collection;

public class ClientResponse {

  private Collection<String> message;

  public Collection<String> getMessage() {
    return message;
  }

  public void setMessage(Collection<String> message) {
    this.message = message;
  }
}
