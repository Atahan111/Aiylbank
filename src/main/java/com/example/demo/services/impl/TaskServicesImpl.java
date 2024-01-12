package com.example.demo.services.impl;

import com.example.demo.dto.SimpleResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.TaskServices;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServicesImpl implements TaskServices {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    @Override
    public SimpleResponse creatTask(TaskRequest taskRequest){
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.isStatus());
        taskRepository.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("created Task")
                .build();
    }

    @Override
    public TaskResponse findTaskById(Long id ){
        return taskRepository.findTaskById(id)
                .orElseThrow(()-> new NotFoundException("Task with id: %s not found ".formatted(id)));
    }




    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("not found taskId " + taskId));

        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.isStatus());

        taskRepository.save(task);
        return TaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .status(task.isStatus())
                .build();
    }

    @Override
    public SimpleResponse deleteTask(Long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("not found taskId " + taskId));
        taskRepository.deleteById(taskId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("  TaskId  deletes")
                .build();
    }
}
