package com.hms.configuration;

import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import com.hms.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppUserRepository userRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        System.out.println(bearerToken);

        if (bearerToken!=null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(8, bearerToken.length() - 1);
            //System.out.println(token);
            String username = jwtService.getUsername(token);
            System.out.println(username);

            Optional<AppUser> optUsername = userRepository.findByUserName(username);

            if(optUsername.isPresent()){
                //later
                AppUser appUser = optUsername.get();
//                System.out.println(appUser.getName());
//                System.out.println(appUser.getEmail());
//                System.out.println(appUser.getUserName());
//                System.out.println(appUser.getPassword());
                UsernamePasswordAuthenticationToken
                        authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                appUser,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }
        }

        filterChain.doFilter(request,response);
    }
}
