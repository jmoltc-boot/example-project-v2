package cl.mobdev.rickandmorty.domain.port.output;

import cl.mobdev.rickandmorty.domain.model.Character;

import java.util.Collection;

public interface GetAllCharactersGateway {

  Collection<Character> execute();

}
