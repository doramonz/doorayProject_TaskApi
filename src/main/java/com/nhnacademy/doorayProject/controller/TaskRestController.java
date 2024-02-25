package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskListDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoListDto;
import com.nhnacademy.doorayProject.dto.task.TaskUploadDto;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskInfoDto> getTask(@PathVariable("taskId") Integer taskId) {
        ResponseEntity<TaskInfoDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(taskService.getTaskInfo(taskId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{projectId}/tasks/list")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable("projectId") Integer projectId) {
        ResponseEntity<TaskListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(taskService.getTaskList(projectId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{projectId}/tasks/list/simple")
    public ResponseEntity<TaskSimpleInfoListDto> getSimpleTaskList(@PathVariable("projectId") Integer projectId) {
        ResponseEntity<TaskSimpleInfoListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(taskService.getSimpleTaskList(projectId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PostMapping("/{projectId}/tasks/upload/{userId}")
    public ResponseEntity<Void> addTask(@PathVariable("projectId") Integer projectId, @PathVariable("userId") String userId, @RequestBody TaskUploadDto taskUploadDto) {
        ResponseEntity<Void> responseEntity;
        try {
            taskService.addTask(projectId, userId, taskUploadDto);
            responseEntity = ResponseEntity.created(null).build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping("/tasks/{taskId}/update/{userId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Integer taskId, @PathVariable("userId") String userId, @RequestBody TaskUploadDto taskUploadDto) {
        ResponseEntity<Void> responseEntity;
        try {
            taskService.updateTask(taskId, userId, taskUploadDto);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e instanceof UnAuthorizedException) {
                responseEntity = ResponseEntity.status(401).build();
            } else {
                responseEntity = ResponseEntity.badRequest().build();
            }
        }
        return responseEntity;
    }

    @DeleteMapping("/tasks/{taskId}/delete/{userId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Integer taskId, @PathVariable("userId") String userId) {
        ResponseEntity<Void> responseEntity;
        try {
            taskService.deleteTask(taskId, userId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e instanceof UnAuthorizedException) {
                responseEntity = ResponseEntity.status(401).build();
            } else {
                responseEntity = ResponseEntity.badRequest().build();
            }
        }
        return responseEntity;
    }

}
