package com.example.demoauth.service;

import com.example.demoauth.models.Cities;
import com.example.demoauth.repository.CitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final CitiesRepository citiesRepository;

    public UserService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<String> getCitiesName(){
        List<Cities> citiesList = citiesRepository.findAll();
        List<String> names = new ArrayList<>();
        citiesList.forEach(res -> {
            Cities cities = new Cities();
            if (res.getStatus()) {
                cities.setCitiesName(res.getCitiesName());
                names.add(cities.getCitiesName());
            }
        });
        return names;
    }

    public List<Cities> getCities(){
        List<Cities> citiesList = citiesRepository.findAll();
        List<Cities> list = new ArrayList<>();
        citiesList.forEach(res -> {
            Cities cities = new Cities();
            if (res.getStatus()) {
                cities.setCitiesName(res.getCitiesName());
                cities.setWeather(res.getWeather());
                cities.setDegree(res.getDegree());
                cities.setId(res.getId());
                cities.setCreationDate(res.getCreationDate());
                cities.setStatus(res.getStatus());
                list.add(cities);
            }
        });
        return list;
    }
}
