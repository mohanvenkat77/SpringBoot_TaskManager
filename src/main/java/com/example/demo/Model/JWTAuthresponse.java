package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthresponse {
    

    private String token;
    private String tokenType="Bearer";

    public JWTAuthresponse(String token){
        this.token=token;
    }
}
