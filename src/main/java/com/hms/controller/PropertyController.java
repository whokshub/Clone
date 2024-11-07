package com.hms.controller;

import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/testing/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addProperty(
            @RequestParam Long locationId,
            @RequestParam Long countryId,
            @RequestParam Long cityId,
            @RequestBody Property property
    ){
        PropertyDto savedProperty = propertyService.createProperty(locationId, cityId, countryId, property);
        return  new ResponseEntity<>(savedProperty,HttpStatus.OK);
    }

    // Delete by Property ID
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deletePropertyById(@PathVariable Long propertyId) {
        propertyService.deletePropertyById(propertyId);
        return ResponseEntity.ok("Property deleted successfully");
    }

    // Delete by Country ID (and related properties)
    @DeleteMapping("/country/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long countryId) {
        propertyService.deletePropertiesByCountryId(countryId);
        propertyService.deleteCountryById(countryId);
        return ResponseEntity.ok("Country and related properties deleted successfully");
    }

    // Delete by Location ID (and related properties)
    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<String> deleteLocationById(@PathVariable Long locationId) {
        propertyService.deletePropertiesByLocationId(locationId);
        propertyService.deleteLocationById(locationId);
        return ResponseEntity.ok("Location and related properties deleted successfully");
    }

    // Delete by City ID (and related properties)
    @DeleteMapping("/city/{cityId}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long cityId) {
        propertyService.deletePropertiesByCityId(cityId);
        propertyService.deleteCityById(cityId);
        return ResponseEntity.ok("City and related properties deleted successfully");
    }

    @GetMapping("/searchHotels")
    public List<Property> searchHotels(@RequestParam String name){

        List<Property> list = propertyService.searchHotelByLocation(name);

        return list;
    }

}


