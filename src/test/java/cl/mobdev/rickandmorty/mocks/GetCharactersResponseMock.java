package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import com.fasterxml.jackson.core.type.TypeReference;

public class GetCharactersResponseMock {

  public static GetCharactersResponse build() {
    String jsonFile = "getCharactersResponse.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<GetCharactersResponse>() {
    });
  }

}
