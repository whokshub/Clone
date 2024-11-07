package com.hms.service;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Location;
import com.hms.entity.Property;
import com.hms.payload.PropertyDto;
import com.hms.repository.CityRepository;
import com.hms.repository.CountryRepository;
import com.hms.repository.LocationRepository;
import com.hms.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.nimbus.State;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{

    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private LocationRepository locationRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;

    public PropertyServiceImpl(CountryRepository countryRepository, CityRepository cityRepository, LocationRepository locationRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.locationRepository = locationRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PropertyDto createProperty(Long locationId, Long cityId, Long countryId, Property property) {

        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();
        Location location = locationRepository.findById(locationId).get();
        property.setCountry(country);
        property.setCity(city);
        property.setLocation(location);

        Property save = propertyRepository.save(property);

        PropertyDto dto = mapToDto(save);

        return dto;
    }

    @Override
    public List<Property> searchHotelByLocation(String name) {
        List<Property> list = propertyRepository.searchHotels(name);

        return list;
    }

    @Transactional
    @Override
    public void deletePropertyById(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
    @Transactional
    @Override
    public void deletePropertiesByCountryId(Long countryId) {
        propertyRepository.deleteByCountryId(countryId);
    }
    @Transactional
    @Override
    public void deleteCountryById(Long countryId) {
        countryRepository.deleteById(countryId);
    }
    @Transactional
    @Override
    public void deletePropertiesByLocationId(Long locationId) {
        propertyRepository.deleteByLocationId(locationId);
    }
    @Transactional
    @Override
    public void deleteLocationById(Long locationId) {
        locationRepository.deleteById(locationId);
    }
    @Transactional
    @Override
    public void deletePropertiesByCityId(Long cityId) {
        propertyRepository.deleteByCityId(cityId);
    }
    @Transactional
    @Override
    public void deleteCityById(Long cityId) {
        cityRepository.deleteById(cityId);
    }

    Property mapToEntity(PropertyDto propertyDto){
        Property property = modelMapper.map(propertyDto, Property.class);
        return property;
    }

    PropertyDto mapToDto(Property property){
        PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
        return propertyDto;
    }

}
