package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Country;
import com.hms.payload.AppUserDto;
import com.hms.payload.CountryDto;
import com.hms.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    private ModelMapper modelMapper;
    private CountryRepository countryRepository;

    public CountryServiceImpl(ModelMapper modelMapper, CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }


    @Override
    public CountryDto addCountry(Country country) {
        Country save = countryRepository.save(country);

        CountryDto countryDto = mapToDto(save);

        return countryDto;
    }



    Country mapToEntity(CountryDto countryDto){

        //To Overcome this problem we use model mapper' map methos that takes two args(needToBeChanged,ChangedInto)
        Country country = modelMapper.map(countryDto, Country.class);
        return country;
    }
    CountryDto mapToDto(Country savedUser){

        //To Overcome this problem we use model mapper' map methos that takes two args(needToBeChanged,ChangedInto)
        CountryDto dto = modelMapper.map(savedUser, CountryDto.class);
        return dto;
    }

}
