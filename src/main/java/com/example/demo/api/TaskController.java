package com.example.demo.api;

import com.example.demo.dto.SimpleResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.services.TaskServices;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServices taskServices;

    @PermitAll
    @PostMapping("/save")
    public SimpleResponse createdTask(@RequestBody TaskRequest taskRequest){
        return  taskServices.creatTask(taskRequest);
    }

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId){
        return taskServices.findTaskById(taskId);
    }

    @PermitAll
    @GetMapping
    public List<Task> getAll(){
        return taskServices.getAllTask();
    }

    @PermitAll
    @PutMapping("{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest){
        return taskServices.updateTask(taskId, taskRequest);
    }

    @PermitAll
    @DeleteMapping("{taskId}")
    public SimpleResponse deletedTask(@PathVariable Long taskId){
        return  taskServices.deleteTask(taskId);
    }
}
