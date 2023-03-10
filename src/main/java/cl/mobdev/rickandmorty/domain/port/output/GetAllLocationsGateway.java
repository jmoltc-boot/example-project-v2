package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Location;

import java.util.Collection;
import java.util.Set;

public interface GetAllLocationsGateway {

  Collection<Location> execute(Set<String> ids);

}
