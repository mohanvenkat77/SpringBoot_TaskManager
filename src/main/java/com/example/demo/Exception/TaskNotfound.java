package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotfound extends RuntimeException {

    private String message;
    public TaskNotfound(String message){
        super(message);
        this.message=message;
    }
    
}
