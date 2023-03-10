package cl.mobdev.rickandmorty.domain.model;

public class Character {

  private Integer id;
  private String name;
  private String status;
  private String species;
  private String type;
  private String gender;
  private Location origin;
  private Location location;

  public static Character.CharacterBuilder builder() {
    return new Character.CharacterBuilder();
  }

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

  public Location getOrigin() {
    return origin;
  }

  public void setOrigin(Location origin) {
    this.origin = origin;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public static final class CharacterBuilder {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Location origin;
    private Location location;

    private CharacterBuilder() {
    }

    public Character.CharacterBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public Character.CharacterBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public Character.CharacterBuilder withStatus(String status) {
      this.status = status;
      return this;
    }

    public Character.CharacterBuilder withSpecies(String species) {
      this.species = species;
      return this;
    }

    public Character.CharacterBuilder withType(String type) {
      this.type = type;
      return this;
    }

    public Character.CharacterBuilder withGender(String gender) {
      this.gender = gender;
      return this;
    }

    public Character.CharacterBuilder withOrigin(Location origin) {
      this.origin = origin;
      return this;
    }

    public Character.CharacterBuilder withLocation(Location location) {
      this.location = location;
      return this;
    }

    public Character build() {
      Character character = new Character();
      character.setId(this.id);
      character.setName(this.name);
      character.setStatus(this.status);
      character.setSpecies(this.species);
      character.setType(this.type);
      character.setGender(this.gender);
      character.setOrigin(this.origin);
      character.setLocation(this.location);
      return character;
    }

  }
}
