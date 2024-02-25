package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskInfoDto> getTask(@PathVariable("taskId") Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskInfo(taskId));
    }
}
