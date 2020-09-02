package weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.entity.Country;
import weather.entity.Location;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
