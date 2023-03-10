package cl.mobdev.rickandmorty.domain.model;

public class Location {

  private Integer id;
  private String name;
  private String type;
  private String dimension;

  public static Location.LocationBuilder builder() {
    return new Location.LocationBuilder();
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDimension() {
    return dimension;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public static final class LocationBuilder {
    private Integer id;
    private String name;
    private String type;
    private String dimension;

    private LocationBuilder() {
    }

    public Location.LocationBuilder withId(Integer id) {
      this.id = id;
      return this;
    }

    public Location.LocationBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public Location.LocationBuilder withType(String type) {
      this.type = type;
      return this;
    }

    public Location.LocationBuilder withDimension(String dimension) {
      this.dimension = dimension;
      return this;
    }

    public Location build() {
      Location location = new Location();
      location.setId(this.id);
      location.setName(this.name);
      location.setType(this.type);
      location.setDimension(this.dimension);
      return location;
    }

  }

}
