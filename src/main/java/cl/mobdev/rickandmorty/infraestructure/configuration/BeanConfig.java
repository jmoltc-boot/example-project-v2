package cl.mobdev.rickandmorty.infraestructure.configuration;

import cl.mobdev.rickandmorty.domain.port.input.GetAllCharactersSortByName;
import cl.mobdev.rickandmorty.domain.usecase.GetAllCharactersSortByNameUseCase;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetAllCharactersFromRickAndMortyApiGateway;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.GetAllLocationsFromRickAndMortyGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public GetAllCharactersSortByName getAllCharactersSortByName(
      GetAllCharactersFromRickAndMortyApiGateway characterGateway,
      GetAllLocationsFromRickAndMortyGateway locationGateway) {
    return new GetAllCharactersSortByNameUseCase(characterGateway, locationGateway);
  }

}
