package cl.mobdev.rickandmorty.domain.port.input;

import cl.mobdev.rickandmorty.domain.model.Character;

import java.util.Collection;

public interface GetAllCharactersSortByName {

  Collection<Character> execute();

}
