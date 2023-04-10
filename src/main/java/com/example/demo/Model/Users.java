package com.example.demo.Model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})
})
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name",nullable=false)
    private String name;
    @Column(name ="email",nullable=false)
    private String email;
    @Column(name ="password",nullable=false)
    private String password;
    public Users(String email2, String password2, Collection<? extends GrantedAuthority> useAuthorities) {
    }
    
}