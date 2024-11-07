package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.payload.LogInDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserService {

    public AppUserDto  addUser(AppUser appUser);

    public List<AppUserDto> getAllUsers();

    public String verifyLogin(LogInDto dto);

}
