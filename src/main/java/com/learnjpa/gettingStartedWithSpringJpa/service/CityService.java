package com.learnjpa.gettingStartedWithSpringJpa.service;

import com.learnjpa.gettingStartedWithSpringJpa.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.CityRequestDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.City;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    public City getCity(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("city with cityId: " + cityId + "could not be found"));
    }

    public City deleteCity(Long cityId) {
        City city = getCity(cityId);

        cityRepository.delete(city);
        return city;
    }


    public City editCity(Long cityId, CityRequestDto cityRequestDto){
    City cityToEdit= getCity(cityId);
    cityToEdit.setName(cityToEdit.getName());
    return cityToEdit;
    }

}
