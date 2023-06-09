package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    public Optional<Users> findByEmail(String email);
    
}
