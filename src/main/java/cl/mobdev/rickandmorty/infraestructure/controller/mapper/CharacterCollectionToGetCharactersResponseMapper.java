package cl.mobdev.rickandmorty.infraestructure.controller.mapper;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.ApiLocation;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class CharacterCollectionToGetCharactersResponseMapper {

  public GetCharactersResponse mappingFrom(Collection<Character> characters) {
    GetCharactersResponse response = new GetCharactersResponse();
    long total = characters.size();

    for (Character c : characters) {
      ApiCharacter apiCharacter = new ApiCharacter();

      apiCharacter.setId(c.getId());
      apiCharacter.setName(c.getName());
      apiCharacter.setGender(c.getGender());
      apiCharacter.setSpecies(c.getSpecies());
      apiCharacter.setStatus(c.getStatus());
      apiCharacter.setType(c.getType());
      apiCharacter.setOrigin(convertLocationToPlace(c.getOrigin()));
      apiCharacter.setLocation(convertLocationToPlace(c.getLocation()));

      response.addCharactersItem(apiCharacter);
    }

    response.setTotal(total);

    return response;
  }

  private ApiLocation convertLocationToPlace(Location location) {
    ApiLocation apiLocation = new ApiLocation();

    apiLocation.setId(location.getId());
    apiLocation.setName(location.getName());
    apiLocation.setType(location.getType());
    apiLocation.setDimension(location.getDimension());

    return apiLocation;
  }

}
