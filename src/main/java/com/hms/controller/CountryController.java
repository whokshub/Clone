package com.hms.controller;

import com.hms.entity.Country;
import com.hms.payload.CountryDto;
import com.hms.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing/country")
public class CountryController {

    // http://localhost:8080/api/testing/country/addcountry

//    @PostMapping("/addcountry")
//    public String addCountry(){
//
//        return "added";
//
//    }
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping("/addcountry")
    public ResponseEntity<?> addCountry(@RequestBody Country country){
        CountryDto addedCountry = countryService.addCountry(country);

        return  new ResponseEntity<>(addedCountry, HttpStatus.OK);
    }


}
