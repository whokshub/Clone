package com.hms.service;

import com.hms.entity.City;
import com.hms.payload.CityDto;
import com.hms.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    private ModelMapper modelMapper;
    private CityRepository cityRepository;

    public CityServiceImpl(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto addCity(City city) {

        City save = cityRepository.save(city);
        CityDto cityDto = mapToDto(save);
        return cityDto;
    }




    City mapToEntity(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
        return city;
    }
    CityDto mapToDto(City city){
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        return cityDto;
    }

}
