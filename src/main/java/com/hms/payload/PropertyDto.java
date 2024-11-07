package com.hms.payload;

import com.hms.entity.City;
import com.hms.entity.Country;
import com.hms.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PropertyDto {

    private String name;
    private Integer noOfGuests;
    private Integer noOfBedrooms;
    private Integer noOfBeds;
    private String noOfBathrooms;
    private Country country;
    private City city;
    private Location location;

}
