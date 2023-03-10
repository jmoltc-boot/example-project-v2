package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.exception.GatewayException;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.mapper.ClientLocationToLocationMapper;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.mocks.ClientLocationMock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
class GetAllLocationsFromRickAndMortyApiGatewayTest {

  private final String someUrl = "http://some-url";
  private final String locationEndpoint = "/location/";
  private final List<String> ids = Arrays.asList("3", "20", "2", "21", "4", "5", "6", "1");

  private GetAllLocationsFromRickAndMortyGateway gateway;

  @Mock
  private RestTemplate restTemplate;
  @Mock
  private ClientLocationToLocationMapper mapper;

  @BeforeEach
  void init() {
    gateway = new GetAllLocationsFromRickAndMortyGateway(restTemplate, someUrl, mapper);
  }

  @Test
  void should_call_external_api_and_map_the_results() {
    final int numberOfInvocationsExpected = 1;
    List<ClientLocation> listOfClientLocationExpected = ClientLocationMock.buildList(ids);

    //GIVEN - Arrange
    String url = someUrl + locationEndpoint + "1, 2, 3, 4, 5, 6, 20, 21";
    ClientLocation[] arrayOfClientLocation = ClientLocationMock.buildArray(ids);
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(arrayOfClientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity(url, ClientLocation[].class)).thenReturn(responseEntityWithOk);

    //WHEN - Act
    gateway.execute(new HashSet<>(ids));

    //THEN - Assert
    verify(mapper, times(numberOfInvocationsExpected)).mappingFrom(listOfClientLocationExpected);
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientLocation[].class);
  }

  @Test
  void should_throw_error_when_api_location_response_is_null() {
    final String messageExpected = "Error Location RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl + locationEndpoint + "1, 2, 3, 4, 5, 6, 20, 21";
    ClientLocation[] arrayOfClientLocation = null;
    ResponseEntity responseEntityWithOk = new ResponseEntity<>(arrayOfClientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity(url, ClientLocation[].class)).thenReturn(responseEntityWithOk);

    //WHEN
    Set<String> someIds = new HashSet<String>(ids);
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute(someIds)
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientLocation[].class);
  }

  @Test
  void should_throw_error_when_api_location_throw_some_exception() {
    final String messageExpected = "Error Location RickAndMorty API";
    final int numberOfInvocationsExpected = 1;

    //GIVEN
    String url = someUrl + locationEndpoint + "1, 2, 3, 4, 5, 6, 20, 21";
    Exception oneException = new RestClientException("some error");
    when(restTemplate.getForEntity(url, ClientLocation[].class)).thenThrow(oneException);

    //WHEN
    Set<String> someIds = new HashSet<String>(ids);
    GatewayException thrown = assertThrows(
        GatewayException.class,
        () -> gateway.execute(someIds)
    );

    //THEN
    assertTrue(thrown.getMessage().contains(messageExpected));
    verify(restTemplate, times(numberOfInvocationsExpected)).getForEntity(url, ClientLocation[].class);
  }
}
