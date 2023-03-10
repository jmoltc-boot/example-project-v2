package cl.mobdev.rickandmorty.integration.success;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.api.model.GetCharactersResponse;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientLocation;
import cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model.ClientResponse;
import cl.mobdev.rickandmorty.mocks.ClientLocationMock;
import cl.mobdev.rickandmorty.mocks.ClientResponseMock;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetCharactersSuccessIntegrationTest {

  private final List<String> ids = Arrays.asList("3", "20", "2", "21", "4", "5", "6", "1");
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
  void should_return_200_and_valid_response() throws Exception {
    int statusExpected = 200;
    GetCharactersResponse responseExpected = buildObjectFromFile();

    //GIVEN
    ClientResponse clientResponse = ClientResponseMock.build();
    String urlCharacterApi = "https://some-url/character";
    ResponseEntity responseOfCharacterApiWithOk = new ResponseEntity(clientResponse, HttpStatus.OK);
    when(restTemplate.getForEntity(urlCharacterApi, ClientResponse.class)).thenReturn(responseOfCharacterApiWithOk);

    ClientLocation[] arrayOfClientLocation = ClientLocationMock.buildArray(ids);
    String urlLocationApi = "https://some-url/location/1, 2, 3, 4, 5, 6, 20, 21";
    ResponseEntity responseOfLocationApiWithOk = new ResponseEntity<>(arrayOfClientLocation, HttpStatus.OK);
    when(restTemplate.getForEntity(urlLocationApi, ClientLocation[].class)).thenReturn(responseOfLocationApiWithOk);

    //WHEN
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/characters")
            .headers(headers))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    GetCharactersResponse response = buildObjectFromString(mvcResult.getResponse().getContentAsString());

    //THEN
    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(statusExpected);
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(contentTypeExpected);
    assertThat(response).isEqualTo(responseExpected);
  }

  private GetCharactersResponse buildObjectFromFile() {
    String jsonFile = "characters_response.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<GetCharactersResponse>() {
    });
  }

  private GetCharactersResponse buildObjectFromString(String json) {
    return TestUtil.buildObjectFromString(json, new TypeReference<GetCharactersResponse>() {
    });
  }

}
