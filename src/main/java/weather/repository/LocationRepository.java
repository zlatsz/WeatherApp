package weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weather.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
