package com.hms.controller;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addcity")
    public ResponseEntity<?> addCCity(@RequestBody City city){
        CityDto cityDto = cityService.addCity(city);

        return new ResponseEntity<>(cityDto,HttpStatus.OK);
    }
}
