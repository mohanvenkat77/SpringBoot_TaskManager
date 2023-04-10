package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="task")
public class Task {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name="taskName",nullable=false)
   private String task;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
   private Users user;
}
