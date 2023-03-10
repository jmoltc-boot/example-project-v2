package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class ClientCharacterToCharacterMapper {

  private static final String EMPTY_STRING = "";

  public Collection<Character> mappingFrom(Collection<ClientCharacter> clientCharacters) {
    Collection<Character> characters = new ArrayList<>();

    for (ClientCharacter clientCharacter : clientCharacters) {
      characters.add(Character.builder()
          .withId(clientCharacter.getId())
          .withName(clientCharacter.getName())
          .withGender(clientCharacter.getGender())
          .withSpecies(clientCharacter.getSpecies())
          .withStatus(clientCharacter.getStatus())
          .withType(clientCharacter.getType())
          .withOrigin(defaultLocation(clientCharacter.getOrigin().getUrl()))
          .withLocation(defaultLocation(clientCharacter.getLocation().getUrl()))
          .build());
    }

    return characters;
  }

  private Location defaultLocation(String url) {
    Location location = Location.builder()
        .withName(EMPTY_STRING)
        .withType(EMPTY_STRING)
        .withDimension(EMPTY_STRING)
        .build();

    if (!EMPTY_STRING.equals(url)) {
      Integer locationId = Arrays.stream(url.split("/"))
          .reduce((a, b) -> b)
          .map(Integer::parseInt)
          .orElse(null);
      location.setId(locationId);
    }

    return location;
  }

}
