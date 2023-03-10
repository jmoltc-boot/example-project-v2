package cl.mobdev.rickandmorty.api.model;

import java.util.Objects;
import cl.mobdev.rickandmorty.api.model.ApiLocation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * ApiCharacter
 */

public class ApiCharacter   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("species")
  private String species = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("origin")
  private ApiLocation origin = null;

  @JsonProperty("location")
  private ApiLocation location = null;

  public ApiCharacter id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ApiCharacter name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiCharacter status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ApiCharacter species(String species) {
    this.species = species;
    return this;
  }

   /**
   * Get species
   * @return species
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public ApiCharacter type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ApiCharacter gender(String gender) {
    this.gender = gender;
    return this;
  }

   /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ApiCharacter origin(ApiLocation origin) {
    this.origin = origin;
    return this;
  }

   /**
   * Get origin
   * @return origin
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public ApiLocation getOrigin() {
    return origin;
  }

  public void setOrigin(ApiLocation origin) {
    this.origin = origin;
  }

  public ApiCharacter location(ApiLocation location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public ApiLocation getLocation() {
    return location;
  }

  public void setLocation(ApiLocation location) {
    this.location = location;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiCharacter apiCharacter = (ApiCharacter) o;
    return Objects.equals(this.id, apiCharacter.id) &&
        Objects.equals(this.name, apiCharacter.name) &&
        Objects.equals(this.status, apiCharacter.status) &&
        Objects.equals(this.species, apiCharacter.species) &&
        Objects.equals(this.type, apiCharacter.type) &&
        Objects.equals(this.gender, apiCharacter.gender) &&
        Objects.equals(this.origin, apiCharacter.origin) &&
        Objects.equals(this.location, apiCharacter.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, status, species, type, gender, origin, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiCharacter {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    species: ").append(toIndentedString(species)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

