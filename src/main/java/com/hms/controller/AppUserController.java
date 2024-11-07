package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import com.hms.payload.LogInDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/testing/user")
public class AppUserController {

    private AppUserService appUserService;
    private AppUserRepository appUserRepo;

    public AppUserController(AppUserService appUserService, AppUserRepository appUserRepo) {
        this.appUserService = appUserService;
        this.appUserRepo = appUserRepo;
    }

    //Signup for user
    @PostMapping("/signupuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser, BindingResult result){
        //Constraints checking
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Existing username checking
        Optional<AppUser> optUserName = appUserRepo.findByUserName(appUser.getUserName());
        if (optUserName.isPresent()){
            return new ResponseEntity<>("This username is already in use", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Existing email checking
        Optional<AppUser> optEmail = appUserRepo.findByEmail(appUser.getEmail());
        if(optEmail.isPresent()){
            return new ResponseEntity<>("This email is already is in use",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //encrypting the password
        String encruptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(4));
        appUser.setPassword(encruptedPassword);

        appUser.setRole("ROLE_USER");

        AppUserDto savedUser = appUserService.addUser(appUser); // check this

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    @GetMapping("/getusers")
    public ResponseEntity<?> getUser(){
        List<AppUserDto> allUsers = appUserService.getAllUsers();

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInDto dto){
        String token = appUserService.verifyLogin(dto);

        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");

            //return new ResponseEntity<>("User Logged in and token is : "+token,HttpStatus.OK);
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid username/password",HttpStatus.FORBIDDEN);
        }

    }

    //signup for owner
    @PostMapping("/signupowner")
    public ResponseEntity<?> createOwner(@Valid @RequestBody AppUser appUser, BindingResult result){
        //Constraints checking
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Existing username checking
        Optional<AppUser> optUserName = appUserRepo.findByUserName(appUser.getUserName());
        if (optUserName.isPresent()){
            return new ResponseEntity<>("This username is already in use", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Existing email checking
        Optional<AppUser> optEmail = appUserRepo.findByEmail(appUser.getEmail());
        if(optEmail.isPresent()){
            return new ResponseEntity<>("This email is already is in use",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //encrypting the password
        String encruptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(4));
        appUser.setPassword(encruptedPassword);
        appUser.setRole("ROLE_OWNER");
        AppUserDto savedUser = appUserService.addUser(appUser); // check this

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
}
