package com.example.demo.SecurityCustom;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.UserNotfound;
import com.example.demo.Model.Users;
import com.example.demo.Repository.UserRepo;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepo urepo; 

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user=urepo.findByEmail(email).orElseThrow(
            ()-> new UserNotfound(String.format("user with email: %s is not found", email))
        );

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_ADMIN");
        return new User(user.getEmail(), user.getPassword(), useAuthorities(roles));
    }
    

    private Collection<? extends GrantedAuthority> useAuthorities(Set<String> roles ){
        return roles.stream().map(
            role -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
    }
}
