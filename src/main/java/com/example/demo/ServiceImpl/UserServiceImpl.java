package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Users;
import com.example.demo.PayLoad.UserDto;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo repo;

    @Override
    public UserDto createUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users data=dtoToEntity(user);
        repo.save(data);
        return entityToDto(data);
        
    }
    

    private Users dtoToEntity(UserDto user){
        Users data=new Users();
        data.setEmail(user.getEmail());
        data.setName(user.getName());
        data.setPassword(user.getPassword());
        return data;
    }


    private UserDto entityToDto(Users user){
        UserDto data=new UserDto();
        data.setEmail(user.getEmail());
        data.setId(user.getId());
        data.setName(user.getName());
        data.setPassword(user.getPassword());
        return data;
    }

}