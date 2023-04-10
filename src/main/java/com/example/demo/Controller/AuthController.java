package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.JWTAuthresponse;
import com.example.demo.Model.LoginDto;
import com.example.demo.PayLoad.UserDto;
import com.example.demo.SecurityCustom.JwtTokenProvider;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(service.createUser(user),HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthresponse> loginUser(@RequestBody LoginDto login){

        Authentication authentication=
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generatetoken(authentication);


        return ResponseEntity.ok(new JWTAuthresponse(token));

    }

}
