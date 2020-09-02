package weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import weather.entity.Country;
import weather.entity.CurrentWeather;
import weather.entity.Location;
import weather.entity.LocationBindingModel;
import weather.repository.CountryRepository;
import weather.repository.LocationRepository;
import weather.service.LiveWeatherService;
import weather.service.LocationService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Controller
public class HomeController {

    private final LiveWeatherService liveWeatherService;
    private final CountryRepository countryRepository;
    private final LocationService locationService;
    private final LocationRepository locationRepository;

    public HomeController(LiveWeatherService liveWeatherService, CountryRepository countryRepository, LocationService locationService, LocationRepository locationRepository) {
        this.liveWeatherService = liveWeatherService;
        this.countryRepository = countryRepository;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/")
    public String add(Model model) {
        List<Country> countries = this.countryRepository.findAll();
        countries.sort(Comparator.comparing(Country::getCountry));
        if (locationRepository.count() > 0) {
            locationRepository.deleteAll();
        }
        Location location = new Location();
        model.addAttribute("countries", countries);
        model.addAttribute("location", location);
        return "index";
    }

    @PostMapping("/")
    public String search(@ModelAttribute(name = "location") LocationBindingModel locationBindingModel, BindingResult bindingResult) {
        this.locationService.add(locationBindingModel);
        return "redirect:current-weather";
    }

    @GetMapping("/current-weather")
    private String result(Model model) {
        Location location = this.locationService.findLast();
        model.addAttribute(location);
        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(location.getName(), location.getCountry()));
        return "current-weather";
    }
}
