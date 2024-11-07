package com.hms.service;

import org.springframework.stereotype.Service;


public interface JWTService {

    public String generateToken(String userName);

    public String getUsername(String token);
}
