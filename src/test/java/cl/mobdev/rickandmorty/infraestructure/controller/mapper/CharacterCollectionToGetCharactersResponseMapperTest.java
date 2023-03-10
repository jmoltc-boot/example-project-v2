package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import cl.mobdev.rickandmorty.mocks.GetCharactersResponseMock;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterCollectionToGetCharactersResponseMapperTest {

  private CharacterCollectionToGetCharactersResponseMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new CharacterCollectionToGetCharactersResponseMapper();
  }

  @Test
  void should_return_a_response_when_receive_a_character_collections() {
    GetCharactersResponse expected = GetCharactersResponseMock.build();

    //GIVEN
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_with_origins_and_locations();

    //WHEN
    GetCharactersResponse response = mapper.mappingFrom(oneCollectionOfCharacters);

    //THEN
    assertEquals(expected.getTotal(), response.getTotal());
    for (ApiCharacter apiCharacter : response.getCharacters()) {
      assertTrue(
          expected.getCharacters()
              .stream().anyMatch(a -> a.getId().equals(apiCharacter.getId())
                  && a.getName().equals(apiCharacter.getName()
              )));
    }
  }

}