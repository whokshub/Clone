package com.hms.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
    @NotNull
    private String userName;
    @Size(min = 3, message = "Name should contain at least three character")
    private String name;
    @Email
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    @NotNull
    @JsonIgnore
    private String role;
}
