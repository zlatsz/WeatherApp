package weather.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import weather.repository.CountryRepository;

@Component
public class DataInit implements CommandLineRunner {
    private final CountryRepository countryRepository;
    private final LocationService locationService;

    public DataInit(CountryRepository countryRepository, LocationService locationService) {
        this.countryRepository = countryRepository;
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (countryRepository.count() == 0) {
            locationService.importJson();
        }
    }
}
