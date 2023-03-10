package cl.mobdev.rickandmorty.infraestructure.controller;

import cl.mobdev.rickandmorty.api.CharactersApi;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.infraestructure.controller.mapper.CharacterCollectionToGetCharactersResponseMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharactersController implements CharactersApi {

  private final GetAllCharactersSortByName getAllCharactersSortByName;
  private final CharacterCollectionToGetCharactersResponseMapper mapperToResponse;

  public CharactersController(GetAllCharactersSortByName getAllCharactersSortByName,
                              CharacterCollectionToGetCharactersResponseMapper mapperToResponse) {
    this.getAllCharactersSortByName = getAllCharactersSortByName;
    this.mapperToResponse = mapperToResponse;
  }

  @Override
  public ResponseEntity<GetCharactersResponse> getCharacters() {
    return ResponseEntity.ok(mapperToResponse.mappingFrom(getAllCharactersSortByName.execute()));
  }
}
