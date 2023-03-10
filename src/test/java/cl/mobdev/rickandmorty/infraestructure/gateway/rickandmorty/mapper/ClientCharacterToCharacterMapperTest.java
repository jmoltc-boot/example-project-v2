package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import cl.mobdev.rickandmorty.mocks.ClientCharacterMock;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientCharacterToCharacterMapperTest {

  private ClientCharacterToCharacterMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new ClientCharacterToCharacterMapper();
  }

  @Test
  void should_return_a_Character_collection() {
    final int sizeExpected = 20;
    Collection<Character> expected = CharacterMock.build_characters_with_origins_and_locations();

    //GIVEN
    Collection<ClientCharacter> oneCollectionOfClientCharacter = ClientCharacterMock.build();

    //WHEN
    Collection<Character> response = this.mapper.mappingFrom(oneCollectionOfClientCharacter);

    //THEN
    assertEquals(sizeExpected, response.size());
    for (Character character : response) {
      assertTrue(
          expected.stream()
              .anyMatch(a -> a.getId().equals(character.getId())
              ));
    }
  }

  @Test
  void should_set_a_default_field_with_empty_strings() {
    String nameExpected = "";
    String typeExpected = "";
    String dimensionExpected = "";

    //GIVEN
    Collection<ClientCharacter> oneCollectionOfClientCharacter = ClientCharacterMock.build();

    //WHEN
    Collection<Character> response = this.mapper.mappingFrom(oneCollectionOfClientCharacter);

    //THEN
    for (Character character : response) {
      assertEquals(nameExpected, character.getOrigin().getName());
      assertEquals(typeExpected, character.getOrigin().getType());
      assertEquals(dimensionExpected, character.getOrigin().getDimension());

      assertEquals(nameExpected, character.getLocation().getName());
      assertEquals(typeExpected, character.getLocation().getType());
      assertEquals(dimensionExpected, character.getLocation().getDimension());
    }
  }

  @Test
  void should_set_origin_id_when_url_exist() {
    Integer[] idsExpected = new Integer[] {3, 20, 2, 21, 4, 5, 6, 1};

    //GIVEN
    Collection<ClientCharacter> oneCollectionOfClientCharacter = ClientCharacterMock.build();

    //WHEN
    Collection<Character> response = this.mapper.mappingFrom(oneCollectionOfClientCharacter);

    //THEN
    Collection<Character> charactersWithOriginId = response.stream()
        .filter(a -> a.getOrigin().getId() != null)
        .collect(Collectors.toList());
    for (Character character : charactersWithOriginId) {
      assertTrue(
          Arrays.stream(idsExpected)
              .anyMatch(a -> a.equals(character.getOrigin().getId())
              ));
    }
  }

  @Test
  void should_set_location_id_when_url_exist() {
    Integer[] idsExpected = new Integer[] {3, 20, 2, 21, 4, 5, 6, 1};

    //GIVEN
    Collection<ClientCharacter> oneCollectionOfClientCharacter = ClientCharacterMock.build();

    //WHEN
    Collection<Character> response = this.mapper.mappingFrom(oneCollectionOfClientCharacter);

    //THEN
    Collection<Character> charactersWithLocationIds = response.stream()
        .filter(a -> a.getLocation().getId() != null)
        .collect(Collectors.toList());
    for (Character character : charactersWithLocationIds) {
      assertTrue(
          Arrays.stream(idsExpected)
              .anyMatch(a -> a.equals(character.getLocation().getId())
              ));
    }
  }

}