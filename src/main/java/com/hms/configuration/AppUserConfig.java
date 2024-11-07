package com.hms.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class AppUserConfig {

    private JWTFilter jwtFilter;

    public AppUserConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //h(cd)2
        http.csrf().disable().cors().disable();

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();

//        http.authorizeHttpRequests().requestMatchers("/api/testing/user/signupuser",
//                        "/api/testing/user/login","/api/testing/user/signupowner")
//                .permitAll().requestMatchers("/api/testing/country/addcountry").hasAnyRole("ADMIN","OWNER")
//    .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
