package cl.mobdev.rickandmorty.infraestructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterCollectionToGetCharactersResponseMapper;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CharactersControllerTest {

  private CharactersController controller;

  @Mock
  private GetAllCharactersSortByName getAllCharactersSortByName;
  @Mock
  private CharacterCollectionToGetCharactersResponseMapper mapperToResponse;

  @BeforeEach
  void setUp() {
    this.controller = new CharactersController(getAllCharactersSortByName, mapperToResponse);
  }

  @Test
  void should_return_200_status_when_call_use_case() {
    final int statusExpected = 200;

    //GIVEN
    Collection<Character> oneEmptyCollectionOfCharacters = new ArrayList<>();
    GetCharactersResponse oneResponseOfApi = new GetCharactersResponse();
    when(getAllCharactersSortByName.execute()).thenReturn(oneEmptyCollectionOfCharacters);
    when(mapperToResponse.mappingFrom(oneEmptyCollectionOfCharacters)).thenReturn(oneResponseOfApi);

    //WHEN
    ResponseEntity responseEntity = controller.getCharacters();

    //THEN
    assertEquals(statusExpected, responseEntity.getStatusCodeValue());
  }

  @Test
  void should_call_one_time_yours_dependencies() {
    final int numberOfTimesExpected = 1;

    //GIVEN
    Collection<Character> oneEmptyCollectionOfCharacters = new ArrayList<>();
    GetCharactersResponse oneResponseOfApi = new GetCharactersResponse();
    when(getAllCharactersSortByName.execute()).thenReturn(oneEmptyCollectionOfCharacters);
    when(mapperToResponse.mappingFrom(oneEmptyCollectionOfCharacters)).thenReturn(oneResponseOfApi);

    //WHEN
    controller.getCharacters();

    //THEN
    verify(getAllCharactersSortByName, times(numberOfTimesExpected)).execute();
    verify(mapperToResponse, times(numberOfTimesExpected)).mappingFrom(oneEmptyCollectionOfCharacters);
  }
  
}