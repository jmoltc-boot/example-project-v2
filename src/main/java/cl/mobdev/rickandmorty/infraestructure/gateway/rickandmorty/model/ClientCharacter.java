package cl.mobdev.rickandmorty.infraestructure.gateway.rickandmorty.model;

public class ClientCharacter {

  private Integer id;
  private String name;
  private String status;
  private String species;
  private String type;
  private String gender;
  private ClientCharacterLocation origin;
  private ClientCharacterLocation location;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ClientCharacterLocation getOrigin() {
    return origin;
  }

  public void setOrigin(ClientCharacterLocation origin) {
    this.origin = origin;
  }

  public ClientCharacterLocation getLocation() {
    return location;
  }

  public void setLocation(ClientCharacterLocation location) {
    this.location = location;
  }

}
