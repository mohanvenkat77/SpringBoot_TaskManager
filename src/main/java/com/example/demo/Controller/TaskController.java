package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PayLoad.TaskDto;
import com.example.demo.Service.TaskService;

import lombok.val;

@RestController
@RequestMapping("/api")
public class TaskController {
    
@Autowired
private TaskService service;

@PostMapping("/{userid}/tasks")
public ResponseEntity<TaskDto> savetask(
    @PathVariable(name="userid") Long id,
    @RequestBody TaskDto task
){
    return new ResponseEntity<TaskDto>(service.saveTask(id, task),HttpStatus.CREATED);
}

@PreAuthorize(value="ROLE_ADMIN")
@GetMapping("/{userid}/tasks")
public ResponseEntity<List<TaskDto>> getAlltasks(
    @PathVariable(name="userid") Long user_id
){
    return new ResponseEntity<List<TaskDto>>(service.getAllTasks(user_id),HttpStatus.OK);
    
}
@PreAuthorize(value="ROLE_ADMIN")
@GetMapping("/{user_id}/task/{id}")
public ResponseEntity<TaskDto> gettask(
    @PathVariable(name = "user_id") Long user_id,
    @PathVariable(name = "id") Long id
){
    return new ResponseEntity<>(service.gettask(user_id, id),HttpStatus.OK);
}

@PreAuthorize(value="ROLE_ADMIN")
@DeleteMapping("/{user_id}/task/{id}")
public ResponseEntity<String> deletetask(
    @PathVariable(name = "user_id") Long user_id,
    @PathVariable(name = "id") Long id
){
    service.deletetask(user_id, id);
    return new ResponseEntity<>("task deleted sucessfully",HttpStatus.OK);
}




}
