package com.example.demo.Service;

import java.util.List;

import com.example.demo.PayLoad.TaskDto;

public interface TaskService {
    
    public TaskDto saveTask(Long user_id,TaskDto task);

    public List<TaskDto> getAllTasks(Long user_id);
    public TaskDto gettask(Long user_id,Long id);
    public void deletetask(Long user_id,Long id);
}
