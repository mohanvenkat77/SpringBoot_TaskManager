package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.TaskNotfound;
import com.example.demo.Exception.UserNotfound;
import com.example.demo.Exception.UsersTasksException;
import com.example.demo.Model.Task;
import com.example.demo.Model.Users;
import com.example.demo.PayLoad.TaskDto;
import com.example.demo.Repository.TaskRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
    

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo urepo;

    @Autowired
    private TaskRepo trepo;
    @Override
    public TaskDto saveTask(Long user_id, TaskDto task) {

        Users user=urepo.findById(user_id).orElseThrow(
            ()-> new UserNotfound(String.format("user id %d is not found", user_id))
        );
        Task data=modelMapper.map(task, Task.class);
        data.setUser(user);
        trepo.save(data);
        return modelMapper.map(data, TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(Long user_id) {
        urepo.findById(user_id).orElseThrow(
            ()-> new TaskNotfound(String.format("user id %d not found", user_id))
        );

        
        List<Task> tasks=trepo.findByUser_id(user_id);
        return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDto gettask(Long user_id,Long id){
        Users user=urepo.findById(user_id).orElseThrow(
            ()-> new UserNotfound(String.format("user id %d is not found", user_id))
        );

        Task task=trepo.findById(id).orElseThrow(
            ()-> new TaskNotfound(String.format("Task id %d not found", id))
        );

        if(user.getId() != task.getUser().getId() ){
            throw new UsersTasksException(String.format("task id %d is not belogs to user %d", id,user_id));
        }

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public void deletetask(Long user_id, Long id) {

        Users user=urepo.findById(user_id).orElseThrow(
            ()-> new UserNotfound(String.format("user id %d is not found", user_id))
        );

        Task task=trepo.findById(id).orElseThrow(
            ()-> new TaskNotfound(String.format("Task id %d not found", id))
        );

        if(user.getId() != task.getUser().getId() ){
            throw new UsersTasksException(String.format("task id %d is not belogs to user %d", id,user_id));
        }
        trepo.deleteById(id);
       
        
    }


}
