package cl.mobdev.rickandmorty.integration.errors;

import static cl.mobdev.rickandmorty.TestUtil.jsonToHashmap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;
import cl.mobdev.rickandmorty.mocks.ClientResponseMock;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetCharactersErrorsIntegrationTest {

  private final String contentTypeExpected = "application/json";
  private MockMvc mockMvc;
  private HttpHeaders headers;

  @MockBean
  private RestTemplate restTemplate;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void setup() {
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.webApplicationContext)
        .build();
  }

  @Test
  void should_return_502_and_error_response_when_character_api_throw_some_error() throws Exception {
    int statusExpected = 502;
    HashMap responseExpected = jsonToHashmap("{\"message\": \"Error Character RickAndMorty API\"}");

    //GIVEN
    String url = "https://some-url/character";
    Exception oneException = new RestClientException("Some message error");
    when(restTemplate.getForEntity(url, ClientResponse.class)).thenThrow(oneException);

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/characters")
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    HashMap response = jsonToHashmap(mvcResult.getResponse().getContentAsString());

    //THEN
    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);
    assertThat(response).isEqualTo(responseExpected);
  }

  @Test
  void should_return_502_and_error_response_when_location_api_throw_some_error() throws Exception {
    int statusExpected = 502;
    HashMap responseExpected = jsonToHashmap("{\"message\": \"Error Location RickAndMorty API\"}");

    //GIVEN
    String urlCharacterApi = "https://some-url/character";
    ClientResponse clientResponse = ClientResponseMock.build();
    ResponseEntity responseOfCharacterApiWithOk = new ResponseEntity(clientResponse, HttpStatus.OK);
    when(restTemplate.getForEntity(urlCharacterApi, ClientResponse.class)).thenReturn(responseOfCharacterApiWithOk);

    String urlLocationApi = "https://some-url/location/1, 2, 3, 4, 5, 6, 20, 21";
    Exception oneException = new RestClientException("Some message error");
    when(restTemplate.getForEntity(urlLocationApi, ClientLocation[].class)).thenThrow(oneException);

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/characters")
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    HashMap response = jsonToHashmap(mvcResult.getResponse().getContentAsString());

    //THEN
    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);
    assertThat(response).isEqualTo(responseExpected);
  }

}
