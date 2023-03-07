package com.learnjpa.gettingStartedWithSpringJpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjpa.gettingStartedWithSpringJpa.DTO.requestData.ZipCodeDto;
import com.learnjpa.gettingStartedWithSpringJpa.entity.ZipCode;
import com.learnjpa.gettingStartedWithSpringJpa.service.ZipCodeService;

@RestController
@RequestMapping("/zipcode")
public class zipCodeController {
    private final ZipCodeService zipCodeService;

    @Autowired
    public zipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<ZipCode> addZipCodes(@RequestBody final ZipCodeDto zipCodeDto) {
        ZipCode zipCode = zipCodeService.addZipCode(zipCodeDto);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ZipCode> getZipcode(@PathVariable final Long id) {
        ZipCode zipCode = zipCodeService.getZipCode(id);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ZipCode>> getZipcodes(){
        List<ZipCode> zipCodes= zipCodeService.getZipCodes();
        return new ResponseEntity<>(zipCodes,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ZipCode> deleteZipcode(@PathVariable final Long id){
        ZipCode zipCode= zipCodeService.deleteZipCode(id);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }


    @PostMapping("/edit/{id}")
    public ResponseEntity<ZipCode> editZipCode(@RequestBody final ZipCodeDto zipCodeRequestDto, @PathVariable final Long id){
        ZipCode zipcode= zipCodeService.editZipCode(id, zipCodeRequestDto);
        return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

    @PostMapping("/addCity/{cityId}/to/{zipCodeId}")
    public ResponseEntity<ZipCode> addCity(@PathVariable final Long cityId, @PathVariable final Long zipCodeId){
        ZipCode zipCode=zipCodeService.addCityToZipCode(zipCodeId, cityId);

        return new ResponseEntity<>(zipCode,HttpStatus.OK);
    }

    @PostMapping("/deletecity/{zipCodeId}")
    public ResponseEntity<ZipCode> deleteCity(@PathVariable final Long zipCodeId){
        ZipCode zipCode= zipCodeService.removeCityFromZipCode(zipCodeId);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

}
