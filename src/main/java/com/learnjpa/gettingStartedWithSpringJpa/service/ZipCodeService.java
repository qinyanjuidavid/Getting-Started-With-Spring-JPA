package com.learnjpa.gettingStartedWithSpringJpa.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.ZipCodeDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.City;
import com.learnjpa.gettingStartedWithSpringJpa.entity.ZipCode;
import com.learnjpa.gettingStartedWithSpringJpa.repository.ZipCodeRepository;

import jakarta.transaction.Transactional;

@Service
public class ZipCodeService {
    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;

    @Autowired
    public ZipCodeService(ZipCodeRepository zipCodeRepository, CityService cityService) {
        this.zipCodeRepository = zipCodeRepository;
        this.cityService = cityService;
    }

    @Transactional
    public ZipCode addZipCode(ZipCodeDto zipCodeDto) {
        ZipCode zipCode = new ZipCode();
        zipCode.setName(zipCodeDto.getName());

        if (zipCodeDto.getCityId() == null) {
            return zipCodeRepository.save(zipCode);
        }
        City city = cityService.getCity(zipCodeDto.getCityId());

        zipCode.setCity(city);
        return zipCodeRepository.save(zipCode);

    }

    public List<ZipCode> getZipCodes() {
        return StreamSupport.stream(zipCodeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public ZipCode getZipCode(Long zipCodeId) {
        return zipCodeRepository.findById(zipCodeId).orElseThrow(
                () -> new IllegalArgumentException("zipcode with id: " + zipCodeId + " could not be found."));
    }

    public ZipCode deleteZipCode(Long zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        zipCodeRepository.delete(zipCode);

        return zipCode;
    }

    @Transactional
    public ZipCode editZipCode(Long zipCodeId, ZipCodeDto zipCodeRequesDto){
        ZipCode zipCodeToEdit= getZipCode(zipCodeId);

        zipCodeToEdit.setName(zipCodeRequesDto.getName());
        
        if (zipCodeRequesDto.getCityId()!=null){
            return zipCodeToEdit;
        }
        City city = cityService.getCity(zipCodeRequesDto.getCityId());
        zipCodeToEdit.setCity(city);

        return zipCodeToEdit;
    }

    @Transactional
    public ZipCode addCityToZipCode(Long zipcodeId, Long cityId){
        ZipCode zipCode=getZipCode(zipcodeId);
        City city = cityService.getCity(cityId);

        if(Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode already has a city");
        }
        zipCode.setCity(city);
        return zipCode;
    }

    @Transactional
    public ZipCode removeCityFromZipCode(Long zipCodeId){
        ZipCode zipCode=getZipCode(zipCodeId);
        

        if(Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("zipcode does not have a city");
        }

        zipCode.setCity(null);
        return zipCode;
        
    }
}
