package com.hms.payload;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDto {

    private Integer rating;
    private String Description;
    private Property property;
    private AppUser appUser;

}
