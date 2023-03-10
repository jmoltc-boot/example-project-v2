package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClientLocationMock {

  private static final Collection<ClientLocation> clientLocationList;

  static {
    String jsonFile = "locationsFromR&M.json";
    clientLocationList = TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<ClientLocation>>() {
    });
  }

  public static Collection<ClientLocation> build() {
    return clientLocationList;
  }

  public static List<ClientLocation> buildList(List<String> ids) {
    return clientLocationList.stream()
        .filter(x -> ids.stream().anyMatch(i -> i.equals(x.getId().toString())))
        .collect(Collectors.toList());
  }

  public static ClientLocation[] buildArray(List<String> ids) {
    return clientLocationList.stream()
        .filter(x -> ids.stream().anyMatch(i -> i.equals(x.getId().toString())))
        .toArray(ClientLocation[]::new);
  }

}
