package weather.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country extends BaseEntity {

    private String country;

    public Country() {
    }

    public Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
