package cl.mobdev.rickandmorty.domain.usecase;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetAllLocationsGateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class GetAllCharactersSortByNameUseCase implements GetAllCharactersSortByName {

  private static Location locationUnknown = Location.builder()
      .withName("unknown")
      .withDimension("")
      .withType("")
      .build();
  private final GetAllCharactersGateway getAllCharactersGateway;
  private final GetAllLocationsGateway getAllLocationsGateway;
  private final Comparator<Character> comparator = Comparator.comparing(Character::getName);

  public GetAllCharactersSortByNameUseCase(GetAllCharactersGateway getAllCharactersGateway,
                                           GetAllLocationsGateway getAllLocationsGateway) {
    this.getAllCharactersGateway = getAllCharactersGateway;
    this.getAllLocationsGateway = getAllLocationsGateway;
  }

  public Collection<Character> execute() {
    Collection<Character> charactersWithoutLocation = getAllCharactersGateway.execute();

    Set<String> locationsIds = extractLocationIdsFrom(charactersWithoutLocation);
    Collection<Location> locations = getAllLocationsGateway.execute(locationsIds);
    Collection<Character> characters = findLocations(charactersWithoutLocation, locations);

    return characters.stream().sorted(comparator).collect(Collectors.toList());
  }

  private Set<String> extractLocationIdsFrom(Collection<Character> characters) {
    Set<String> locationIds = characters
        .stream()
        .filter(x -> x.getLocation().getId() != null)
        .map(x -> x.getLocation().getId())
        .map(Object::toString)
        .collect(Collectors.toSet());

    Set<String> originIds = characters
        .stream()
        .filter(x -> x.getOrigin().getId() != null)
        .map(x -> x.getOrigin().getId())
        .map(Object::toString)
        .collect(Collectors.toSet());

    locationIds.addAll(originIds);

    return locationIds;
  }

  private Collection<Character> findLocations(Collection<Character> charactersWithoutLocation,
                                              Collection<Location> locations) {

    Collection<Character> characters = new ArrayList<>();

    for (Character character : charactersWithoutLocation) {
      character.setOrigin(locations
          .stream()
          .filter(x -> x.getId().equals(character.getOrigin().getId()))
          .findFirst()
          .orElse(locationUnknown));
      character.setLocation(locations
          .stream()
          .filter(x -> x.getId().equals(character.getLocation().getId()))
          .findFirst()
          .orElse(locationUnknown));
      characters.add(character);
    }

    return characters;
  }

}
