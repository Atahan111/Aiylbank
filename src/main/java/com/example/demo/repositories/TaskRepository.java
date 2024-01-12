package com.example.demo.repositories;

import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select new com.example.demo.dto.TaskResponse(t.id,t.description,t.status) from Task t where t.id = :id")
    Optional<TaskResponse> findTaskById(Long id);
}

