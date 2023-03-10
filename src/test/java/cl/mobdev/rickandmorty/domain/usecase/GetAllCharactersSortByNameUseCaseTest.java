package cl.mobdev.rickandmorty.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.domain.port.output.GetAllLocationsGateway;
import cl.mobdev.rickandmorty.mocks.CharacterMock;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllCharactersSortByNameUseCaseTest {

  private final List ids = Arrays.asList("3", "20", "2", "21", "4", "5", "6", "1");
  private GetAllCharactersSortByNameUseCase useCase;

  @Mock
  private GetAllCharactersGateway getAllCharactersGateway;
  @Mock
  private GetAllLocationsGateway getAllLocationsGateway;

  @BeforeEach
  public void setUp() {
    useCase = new GetAllCharactersSortByNameUseCase(getAllCharactersGateway, getAllLocationsGateway);
  }

  @Test
  void should_return_characters_collections_sort_by_name() {
    final String[] namesExpected = charactersSortedByNames();

    //GIVEN
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_without_locations();
    when(getAllCharactersGateway.execute()).thenReturn(oneCollectionOfCharacters);

    //WHEN
    Iterator<Character> it = useCase.execute().iterator();

    //THEN
    int index = 0;
    while (it.hasNext()) {
      assertEquals(namesExpected[index], it.next().getName());
      index++;
    }
  }

  @Test
  void should_call_location_api_with_locations_ids_extract_from_characters() {
    final int numberOfInvocationsExpected = 1;
    final Set<String> idsExpected = new HashSet<>(ids);

    //GIVEN
    Set<String> withSomeIds = new HashSet<>(ids);
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_without_locations();
    Collection<Location> oneCollectionOfLocations = CharacterMock.build_locations_only();
    when(getAllCharactersGateway.execute()).thenReturn(oneCollectionOfCharacters);
    when(getAllLocationsGateway.execute(withSomeIds)).thenReturn(oneCollectionOfLocations);

    //WHEN
    useCase.execute();

    //THEN
    verify(getAllLocationsGateway, times(numberOfInvocationsExpected)).execute(idsExpected);
  }

  @Test
  void should_set_unknown_location_when_id_location_is_null() {
    final String nameExpected = "unknown";
    final String dimensionExpected = "";
    final String typeExpected = "";

    //GIVEN
    Set<String> withSomeIds = new HashSet<>(ids);
    Collection<Character> oneCollectionOfCharacters = CharacterMock.build_characters_without_locations();
    Collection<Location> oneCollectionOfLocations = CharacterMock.build_locations_only();
    when(getAllCharactersGateway.execute()).thenReturn(oneCollectionOfCharacters);
    when(getAllLocationsGateway.execute(withSomeIds)).thenReturn(oneCollectionOfLocations);

    //WHEN
    Collection<Character> response = useCase.execute();

    //THEN
    Collection<Character> charactersWithIdNull = response.stream()
        .filter(x -> null == x.getLocation().getId())
        .collect(Collectors.toList());
    for (Character character : charactersWithIdNull) {
      assertEquals(nameExpected, character.getLocation().getName());
      assertEquals(dimensionExpected, character.getLocation().getDimension());
      assertEquals(typeExpected, character.getLocation().getType());
    }
  }

  private String[] charactersSortedByNames() {
    return new String[] {
        "Abadango Cluster Princess",
        "Abradolf Lincler",
        "Adjudicator Rick",
        "Agency Director",
        "Alan Rails",
        "Albert Einstein",
        "Alexander",
        "Alien Googah",
        "Alien Morty",
        "Alien Rick",
        "Amish Cyborg",
        "Annie",
        "Antenna Morty",
        "Antenna Rick",
        "Ants in my Eyes Johnson",
        "Beth Smith",
        "Jerry Smith",
        "Morty Smith",
        "Rick Sanchez",
        "Summer Smith"
    };
  }

}
