package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.payload.LogInDto;
import com.hms.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto addUser(AppUser appUser) {
        AppUser savedUser = appUserRepository.save(appUser);

        //Copy from Entity to Dto
        //second way by calling method
        AppUserDto dto = mapToDto(savedUser);

        //first way by directly setting the values
//        AppUserDto dto = new AppUserDto();
//        dto.setUserName(savedUser.getUserName());
//        dto.setName(savedUser.getName());
//        dto.setEmail(savedUser.getEmail());
//        dto.setPassword(savedUser.getPassword());

        return dto;

    }

    @Override
    public List<AppUserDto> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();

        List<AppUserDto> allUsers = users.stream().map(userss -> mapToDto(userss)).collect(Collectors.toList());

        return allUsers;
    }

    AppUser mapToEntity(AppUserDto appUserDto){

        //To Overcome this problem we use model mapper' map methos that takes two args(needToBeChanged,ChangedInto)
        AppUser user = modelMapper.map(appUserDto, AppUser.class);

        //by using the below method we have to call the setter and getter for all variable that increases the code lines
//        AppUser user = new AppUser();
//        user.setUserName(appUserDto.getUserName());
//        user.setName(appUserDto.getName());
//        user.setEmail(appUserDto.getEmail());
//        user.setPassword(appUserDto.getPassword());

        return user;
    }
    AppUserDto mapToDto(AppUser savedUser){

        //To Overcome this problem we use model mapper' map methos that takes two args(needToBeChanged,ChangedInto)
        AppUserDto dto = modelMapper.map(savedUser, AppUserDto.class);

//by using the below method we have to call the setter and getter for all variable that increases the code lines
//        AppUserDto dto = new AppUserDto();
//        dto.setUserName(savedUser.getUserName());
//        dto.setName(savedUser.getName());
//        dto.setEmail(savedUser.getEmail());
//        dto.setPassword(savedUser.getPassword());

        return dto;
    }
    @Override
    public String verifyLogin(LogInDto dto){
        Optional<AppUser> optUser = appUserRepository.findByUserName(dto.getUserName());

        if(optUser.isPresent()){
            AppUser appUser = optUser.get();
            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
                return jwtService.generateToken(appUser.getUserName());
            }
            else {
                return null;
            }
        }else{
            return null;
        }

    }

}
