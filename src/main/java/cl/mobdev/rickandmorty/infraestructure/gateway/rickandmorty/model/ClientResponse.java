package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model;

import java.util.Collection;

public class ClientResponse {

  private Collection<ClientCharacter> clientCharacterCollections;

  public Collection<ClientCharacter> getResults() {
    return clientCharacterCollections;
  }

  public void setResults(Collection<ClientCharacter> clientCharacterResponse) {
    this.clientCharacterCollections = clientCharacterResponse;
  }

}
