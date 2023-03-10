package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;

public class ClientResponseMock {

  public static ClientResponse build() {
    String jsonFile = "charactersFromR&M.json";
    ClientResponse clientResponse = new ClientResponse();
    Collection<ClientCharacter> results =
        TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<ClientCharacter>>() {
        });
    clientResponse.setResults(results);
    return clientResponse;
  }

}


