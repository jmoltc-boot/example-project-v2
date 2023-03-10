package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientCharacterToCharacterMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;
import cl.mobdev.rickandmorty.mocks.ClientResponseMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class GetAllCharactersFromRickAndMortyApiGatewayTest {

  private final String someUrl = "http://some-url";
  private final String characterEndpoint = "/character";

  private GetAllCharactersFromRickAndMortyApiGateway gateway;

  @Mock
  private RestTemplate restTemplate;
  @Mock
  private ClientCharacterToCharacterMapper mapper;

  @BeforeEach
  void init() {
    gateway = new GetAllCharactersFromRickAndMortyApiGateway(restTemplate, someUrl, mapper);
  }

  @Test
  void should_call_external_api_and_map_the_results() {
    final int numberOfInvocationsExpected = 1;

    //GIVEN - Arrange
    String url = someUrl.concat(characterEndpoint);
    ClientResponse clientResponse = ClientResponseMock.build();
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientResponse, HttpStatus.OK);
    when(restTemplate.getForEntity(url, ClientResponse.class)).thenReturn(responseEntityWithOk);

    //WHEN - Act
    gateway.execute();

    //THEN - Assert
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientResponse.class);
    verify(mapper, times(numberOfInvocationsExpected)).mappingFrom(clientResponse.getResults());
  }

  @Test
  void should_throw_some_error_when_api_character_response_is_null() {
    final String messageExpected = "Error Character RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl.concat(characterEndpoint);
    ClientResponse clientResponse = null;
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(clientResponse, HttpStatus.OK);
    when(restTemplate.getForEntity(url, ClientResponse.class)).thenReturn(responseEntityWithOk);

    //WHEN
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute()
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientResponse.class);
  }

  @Test
  void should_throw_some_error_when_api_character_call_fails() {
    final String messageExpected = "Error Character RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl.concat(characterEndpoint);
    Exception oneException = new RestClientException("some error");
    when(restTemplate.getForEntity(url, ClientResponse.class)).thenThrow(oneException);

    //WHEN
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute()
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientResponse.class);
  }

}
