package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.output.GetAllCharactersGateway;
import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientCharacterToCharacterMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientCharacter;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GetAllCharactersFromRickAndMortyApiGateway implements GetAllCharactersGateway {

  private static final String CHARACTER_ENDPOINT = "/character";

  private final RestTemplate restTemplate;
  private final String urlApi;
  private final ClientCharacterToCharacterMapper mapper;

  public GetAllCharactersFromRickAndMortyApiGateway(RestTemplate restTemplate,
                                                    @Value("${urlApi.rickAndMorty}") String urlApi,
                                                    ClientCharacterToCharacterMapper mapper) {
    this.restTemplate = restTemplate;
    this.urlApi = urlApi + CHARACTER_ENDPOINT;
    this.mapper = mapper;
  }

  @Override
  public Collection<Character> execute() {
    Collection<ClientCharacter> clientCharacterCollection = getAllCharactersFromApi();

    return mapper.mappingFrom(clientCharacterCollection);
  }

  private Collection<ClientCharacter> getAllCharactersFromApi() {
    ResponseEntity<ClientResponse> response;
    ClientResponse clientResponse;

    try {
      response = restTemplate.getForEntity(urlApi, ClientResponse.class);
      clientResponse = response.getBody();

      if (null == clientResponse) {
        throw new GatewayException("Error Character RickAndMorty API");
      }

      return clientResponse.getResults();
    } catch (RestClientException e) {
      throw new GatewayException("Error Character RickAndMorty API");
    }
  }

}
