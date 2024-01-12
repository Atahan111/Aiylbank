package com.example.demo.services;

import com.example.demo.dto.SimpleResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;

import java.util.List;

public interface TaskServices {
    SimpleResponse creatTask(TaskRequest taskRequest);

    List<Task> getAllTask();

    TaskResponse findTaskById(Long id);

    TaskResponse updateTask(Long taskId, TaskRequest taskRequest);

    SimpleResponse deleteTask(Long taskId);
}
