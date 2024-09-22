package backend.project.servicesimpl;

import backend.project.entities.City;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CityRepository;
import backend.project.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City insertCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City insertCity(String name) {
        City city = new City();
        city.setName(name);
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("City not found with ID: " + id);
        }
        cityRepository.deleteById(id);
    }

    @Override
    public void deleteCity(Long id, boolean forced) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("City not found with ID: " + id);
        }

        if (forced) {
            cityRepository.deleteById(id);
        } else {
            City city = cityRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + id));
            cityRepository.delete(city);
        }
    }

    @Override
    public List<City> listAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + id));
    }

    @Override
    public City findByName(String name) {
        City city = cityRepository.findByName(name);
        if (city == null) {
            throw new ResourceNotFoundException("City not found with name: " + name);
        }
        return city;
    }
}
