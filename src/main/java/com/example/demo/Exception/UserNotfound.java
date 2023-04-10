package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotfound  extends RuntimeException{

    private String message;
    public UserNotfound(String message){
        super(message);
        this.message=message;
    }
    
}
