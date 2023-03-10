package cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.model.ClientResponse;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DogCeoResponseToCharacterCollectionsMapperTest {

  private DogCeoResponseToCharacterCollectionsMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new DogCeoResponseToCharacterCollectionsMapper();
  }

  @Test
  void should_return_Characters_collection() {
    final int sizeExpected = 2;

    //GIVEN
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setMessage(Arrays.asList("dog 1", "dog 1"));

    //WHEN
    Collection<Character> response = mapper.mappingFrom(clientResponse);

    //THEN
    assertEquals(sizeExpected, response.size());
  }

}