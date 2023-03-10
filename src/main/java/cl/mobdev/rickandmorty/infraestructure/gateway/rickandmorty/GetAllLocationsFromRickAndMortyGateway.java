package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import cl.mobdev.rickandmorty.domain.model.Location;
import cl.mobdev.rickandmorty.domain.port.output.GetAllLocationsGateway;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientLocationToLocationMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GetAllLocationsFromRickAndMortyGateway implements GetAllLocationsGateway {

  private static final String LOCATION_ENDPOINT = "/location/";

  private final RestTemplate restTemplate;
  private final String urlApi;
  private final ClientLocationToLocationMapper mapper;

  public GetAllLocationsFromRickAndMortyGateway(RestTemplate restTemplate,
                                                @Value("${urlApi.rickAndMorty}") String urlApi,
                                                ClientLocationToLocationMapper mapper) {
    this.restTemplate = restTemplate;
    this.urlApi = urlApi + LOCATION_ENDPOINT;
    this.mapper = mapper;
  }

  @Override
  public Collection<Location> execute(Set<String> ids) {
    List<ClientLocation> clientLocationList = getAllLocationsFomApi(buildIds(ids));

    return this.mapper.mappingFrom(clientLocationList);
  }

  private List<ClientLocation> getAllLocationsFomApi(String locationsIds) {
    ResponseEntity<ClientLocation[]> response;
    ClientLocation[] arrayOfClientLocations;

    try {
      String url = urlApi + locationsIds;
      response = restTemplate.getForEntity(url, ClientLocation[].class);
      arrayOfClientLocations = response.getBody();

      if (null == arrayOfClientLocations) {
        throw new GatewayException("Error Location RickAndMorty API");
      }

      return Arrays.asList(arrayOfClientLocations);
    } catch (RestClientException e) {
      throw new GatewayException("Error Location RickAndMorty API");
    }
  }

  private String buildIds(Set<String> ids) {
    return ids
        .stream()
        .collect(Collectors.joining(", "));
  }

}
