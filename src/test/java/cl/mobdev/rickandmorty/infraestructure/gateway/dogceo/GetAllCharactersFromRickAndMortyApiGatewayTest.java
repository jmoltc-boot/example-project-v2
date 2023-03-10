package cl.mobdev.rickandmorty.infraestructure.gateway.dogceo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.mapper.DogCeoResponseToCharacterCollectionsMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.dogceo.model.ClientResponse;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class GetAllCharactersFromRickAndMortyApiGatewayTest {

  private final String someUrl = "http://some-url";
  private GetAllCharactersFromDogCeoApiGateway gateway;

  @Mock
  private RestTemplate restTemplate;
  @Mock
  private DogCeoResponseToCharacterCollectionsMapper mapper;

  @BeforeEach
  void setUp() {
    this.gateway = new GetAllCharactersFromDogCeoApiGateway(restTemplate, someUrl, mapper);
  }

  @Test
  void should_call_external_api_and_map_the_results() {
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setMessage(Arrays.asList("dog 1", "dog 1"));
    ResponseEntity oneResponseEntity = new ResponseEntity<>(clientResponse, HttpStatus.OK);
    when(restTemplate.getForEntity(someUrl, ClientResponse.class)).thenReturn(oneResponseEntity);

    //WHEN
    gateway.execute();

    //THEN
    verify(mapper, times(numberOfInvocationsExpected)).mappingFrom(clientResponse);
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(someUrl, ClientResponse.class);
  }
}