package weather.entity;

import javax.persistence.*;

@Entity
@Table(name="location")
public class Location extends BaseEntity{

    private String name;
    private String country;

    public Location() {
    }

    public Location(String name, String country) {
        this.name = name;
        this.country = country;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
