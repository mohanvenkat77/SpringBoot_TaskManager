package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{

    List<Task> findByUser_id(Long user_id);
    
}
