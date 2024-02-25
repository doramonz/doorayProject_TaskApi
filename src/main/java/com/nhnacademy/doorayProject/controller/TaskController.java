package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.dto.TaskDto;
import com.nhnacademy.doorayProject.dto.TaskUpdateDto;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/projects/tasks/{taskId}")
    public Task getTask(@PathVariable("taskId")Integer taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("/projects/{projectId}/tasks/list")
    public List<Task> getTaskList(@PathVariable("projectId")Integer projectId) {
        return taskService.getTaskList(projectId);
    }

    @PostMapping("/projects/{projectId}/tasks/list/simple")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SimpleTaskDto> getSimpleTaskList(@PathVariable("projectId")Integer projectId) {
        return taskService.getSimpleTaskList(projectId);
    }

    @PutMapping("/projects/tasks/{taskId}/update/{userId}")
    public TaskUpdateDto updateTask(@PathVariable("taskId")Integer taskId,
                                    @PathVariable("userId")String userId,
                                    @RequestBody TaskDto taskDto) {
        return taskService.updateTask(taskId, userId, taskDto);
    }

    @DeleteMapping("/projects/tasks/{taskId}/delete/{userId}")
    public void deleteTask(@PathVariable("taskId")Integer taskId,
                           @PathVariable("userId")String userId) {
        taskService.deleteTask(taskId, userId);
    }
}
