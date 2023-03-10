package cl.mobdev.rickandmorty.infraestructure.gateway.dogceo;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.mapper.DogCeoResponseToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.model.ClientResponse;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetAllCharactersFromDogCeoApiGateway implements GetAllCharactersGateway {

  private final RestTemplate restTemplate;
  private final String urlApi;
  private final DogCeoResponseToCharacterCollectionsMapper mapper;

  public GetAllCharactersFromDogCeoApiGateway(RestTemplate restTemplate,
                                              @Value("${urlApi.dogCeo}") String urlApi,
                                              DogCeoResponseToCharacterCollectionsMapper mapper) {
    this.restTemplate = restTemplate;
    this.urlApi = urlApi;
    this.mapper = mapper;
  }

  @Override
  public Collection<Character> execute() {

    ResponseEntity<ClientResponse> response = restTemplate.getForEntity(urlApi, ClientResponse.class);

    return mapper.mappingFrom(response.getBody());
  }
}
