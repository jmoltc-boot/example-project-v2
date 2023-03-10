package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper;

import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ClientLocationToLocationMapper {

  public Collection<Location> mappingFrom(List<ClientLocation> clientLocations) {
    Collection<Location> locations = new ArrayList<>();

    for (ClientLocation clientLocation : clientLocations) {
      locations.add(Location.builder()
          .withId(clientLocation.getId())
          .withName(clientLocation.getName())
          .withType(clientLocation.getType())
          .withDimension(clientLocation.getDimension())
          .build());
    }

    return locations;
  }

}
