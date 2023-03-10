package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;

public class ClientCharacterMock {

  public static Collection<ClientCharacter> build() {
    String jsonFile = "charactersFromR&M.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<ClientCharacter>>() {
    });
  }

}
