package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.mocks.CharacterMock;
import cl.mobdev.rickandmorty.mocks.ClientLocationMock;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientLocationToLocationMapperTest {

  private ClientLocationToLocationMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new ClientLocationToLocationMapper();
  }

  @Test
  void should_return_collection_of_locations() {
    int sizeExpected = 8;
    Collection<Location> locationsExpected = CharacterMock.build_locations_only();

    //GIVEN
    List<ClientLocation> oneListOfClientLocation = ClientLocationMock.build().stream().collect(Collectors.toList());

    //WHEN
    Collection<Location> response = this.mapper.mappingFrom(oneListOfClientLocation);

    //THEN
    assertEquals(sizeExpected, response.size());
    for (Location location : response) {
      assertTrue(
          locationsExpected.stream()
              .anyMatch(a -> a.getId().equals(location.getId())
              ));
    }
  }

}