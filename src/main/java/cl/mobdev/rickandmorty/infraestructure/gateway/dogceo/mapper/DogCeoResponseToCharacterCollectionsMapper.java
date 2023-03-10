package cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.mapper;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.model.ClientResponse;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class DogCeoResponseToCharacterCollectionsMapper {

  public Collection<Character> mappingFrom(ClientResponse clientResponse) {
    Collection<Character> characters = new ArrayList<>();

    for (String s : clientResponse.getMessage()) {
      characters.add(Character.builder()
          .withName(s)
          .withOrigin(new Location())
          .withLocation(new Location())
          .build());
    }

    return characters;
  }

}
