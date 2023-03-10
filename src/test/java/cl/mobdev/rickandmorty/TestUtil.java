package cl.mobdev.rickandmorty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

public class TestUtil {

  public static HashMap jsonToHashmap(String json) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, HashMap.class);
  }

  public static <T> T buildObjectFromFile(String jsonFile, TypeReference<T> type) {
    try {
      File json = ResourceUtils.getFile("classpath:" + jsonFile);
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(json, type);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Problem building object from json file");
    }
  }

  public static <T> T buildObjectFromString(String contentAsString, TypeReference<T> type) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(contentAsString, type);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("Problem building object from string");
    }
  }

}
