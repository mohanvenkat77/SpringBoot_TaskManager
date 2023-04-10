package com.example.demo.Exception;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsersTasksException  extends RuntimeException{
    private String message;
    public UsersTasksException(String message){
        super(message);
        this.message=message;

    }
}
