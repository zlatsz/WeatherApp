package weather.service;

import org.springframework.stereotype.Service;
import weather.entity.Country;
import weather.entity.Location;
import weather.entity.LocationBindingModel;
import weather.repository.CountryRepository;
import com.google.gson.Gson;
import weather.repository.LocationRepository;
import weather.config.FileUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    public static final String FILE_PATH = "src/main/resources/files/city.list.json";
    private final CountryRepository countryRepository;
    private final LocationRepository locationRepository;
    private final Gson gson;
    private final FileUtil fileUtil;

    public LocationServiceImpl(CountryRepository countryRepository, LocationRepository locationRepository, Gson gson, FileUtil fileUtil) {
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public void importJson() throws IOException {
        Country[] dtos = this.gson
                .fromJson(new FileReader(FILE_PATH),
                        Country[].class);
        Arrays.stream(dtos).forEach(dto -> {
            Country country = new Country(dto.getCountry());
            List<Country> all = this.countryRepository.findAll();
            if (all.size() == 0) {
                countryRepository.saveAndFlush(country);
            } else {
                boolean flag = false;
                for (Country c : all) {
                    if ((c.getCountry().equals(country.getCountry()))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    countryRepository.saveAndFlush(country);
                }
            }
        });
    }

    @Override
    public void add(LocationBindingModel locationBindingModel) {
        Location location = new Location(locationBindingModel.getName(), locationBindingModel.getCountry());
        this.locationRepository.saveAndFlush(location);
    }

    @Override
    public Location findLast() {
        Location limit = new Location();
        for (Location location : this.locationRepository.findAll()) {
            limit = location;
            break;
        }
        return limit;
    }
}
