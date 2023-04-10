package com.example.demo.SecurityCustom;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.Exception.UsersTasksException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {
 
    
    public String generatetoken(Authentication authentication){
        String email=authentication.getName();
        Date cdate=new Date();
        Date edate=new Date(cdate.getTime()+3600000);
        String token=Jwts.builder()
                            .setSubject(email)
                            .setIssuedAt(cdate)
                            .setExpiration(edate)
                            .signWith(SignatureAlgorithm.HS512, "JWTSecretKey")
                            .compact();
        return token;
    }


    public String getemailfromToken(String token){
        Claims claims=Jwts.parser().setSigningKey("JWTSecretKey")
            .parseClaimsJws(token).getBody();
            System.out.println("claims is ....."+claims);
            return claims.getSubject();
    }

    public  boolean validateString(String token){
        try {
            Jwts.parser().setSigningKey("JWTSecretKey").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new UsersTasksException("token issue :"+ e.getMessage());
        }
    }




}
