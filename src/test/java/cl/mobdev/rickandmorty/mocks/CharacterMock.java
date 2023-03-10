package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;

public class CharacterMock {

  public static Collection<Character> build_characters_without_locations() {
    String jsonFile = "charactersWithoutLocations.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Character> build_characters_with_origins_and_locations() {
    String jsonFile = "charactersWithOriginAndLocations.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Location> build_locations_only() {
    String jsonFile = "locationsOnly.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Location>>() {
    });
  }

}
