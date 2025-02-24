package com.hms.repository;

import com.hms.entity.AppUser;
import com.hms.payload.AppUserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    Optional<AppUser> findByUserName(String userName);

    Optional<AppUser> findByEmail(String email);




}