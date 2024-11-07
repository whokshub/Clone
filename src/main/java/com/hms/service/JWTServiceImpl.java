package com.hms.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService{

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private Integer expiryTime;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
//        System.out.println(algorithmKey);
//        System.out.println(issuer);
//        System.out.println(expiryTime);
        algorithm = Algorithm.HMAC256(algorithmKey);
    }
    @Override
    public String generateToken(String userName) {
        return JWT.create()
                .withClaim("name",userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    @Override
    public String getUsername(String token){

        DecodedJWT decodedJwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJwt.getClaim("name").asString();

    }
}
