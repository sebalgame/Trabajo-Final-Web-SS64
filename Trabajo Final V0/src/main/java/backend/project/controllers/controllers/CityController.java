package backend.project.controllers;

import backend.project.entities.City;
import backend.project.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City savedCity = cityService.insertCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<City> createCityByName(@RequestParam String name) {
        City savedCity = cityService.insertCity(name);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteCityForced(@PathVariable Long id, @RequestParam boolean forced) {
        cityService.deleteCity(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<City>> listAllCities() {
        List<City> cities = cityService.listAllCities();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        City city = cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<City> getCityByName(@RequestParam String name) {
        City city = cityService.findByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
