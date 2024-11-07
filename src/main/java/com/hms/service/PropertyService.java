package com.hms.service;

import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PropertyService {

    public PropertyDto createProperty(Long locationId, Long cityId, Long countryId, Property property);

    public List<Property> searchHotelByLocation(String name);

    //for deleting
    @Transactional
    public void deletePropertyById(Long propertyId);
    @Transactional
    public void deletePropertiesByCountryId(Long countryId);
    @Transactional
    public void deleteCountryById(Long countryId);
    @Transactional
    public void deletePropertiesByLocationId(Long locationId);
    @Transactional
    public void deleteLocationById(Long locationId);
    @Transactional
    public void deletePropertiesByCityId(Long cityId);
    @Transactional
    public void deleteCityById(Long cityId);
}
