package com.hms.service;

import com.hms.entity.Location;
import com.hms.payload.LocationDto;
import com.hms.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService{

    private ModelMapper modelMapper;
    private LocationRepository locationRepository;

    public LocationServiceImpl(ModelMapper modelMapper, LocationRepository locationRepository) {
        this.modelMapper = modelMapper;
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDto addLocation(Location location) {
        Location save = locationRepository.save(location);
        LocationDto locationDto = mapToDto(save);

        return locationDto;
    }

    Location mapToEntity(LocationDto locationDto){
        Location location = modelMapper.map(locationDto, Location.class);
        return location;
    }

    LocationDto mapToDto(Location location){
        LocationDto locationDto = modelMapper.map(location, LocationDto.class);
        return locationDto;
    }
}
