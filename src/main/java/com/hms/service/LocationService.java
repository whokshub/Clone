package com.hms.service;

import com.hms.entity.Location;
import com.hms.payload.LocationDto;

public interface LocationService {

    public LocationDto addLocation(Location location);
}
