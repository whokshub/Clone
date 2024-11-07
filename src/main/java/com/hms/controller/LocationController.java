package com.hms.controller;

import com.hms.entity.Location;
import com.hms.payload.LocationDto;
import com.hms.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @PostMapping("/addlocation")
    public ResponseEntity<?> addLocation(@RequestBody Location location){
        LocationDto locationDto = locationService.addLocation(location);

        return  new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

}
