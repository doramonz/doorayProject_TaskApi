package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskListDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskUploadDto;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.TaskNotFoundException;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getTaskInfo_exist() {
        Integer taskId = 1;
        String taskTitle = "taskTitle";
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        Task task = new Task();
        task.setTaskTitle(taskTitle);
        taskInfoDto.setTitle(taskTitle);
        Mockito.doReturn(Optional.of(task)).when(taskRepository).findById(taskId);
        Assertions.assertEquals(taskInfoDto.getTitle(), taskService.getTaskInfo(taskId).getTitle());
        Mockito.verify(taskRepository).findById(taskId);
    }

    @Test
    void getTaskInfo_notExist() {
        Integer taskId = 1;
        Mockito.doReturn(Optional.empty()).when(taskRepository).findById(taskId);
        Assertions.assertThrows(TaskNotFoundException.class, () -> taskService.getTaskInfo(taskId));
    }

    @Test
    void getTaskList() {
        Integer projectId = 1;
        TaskListDto taskListDto = new TaskListDto(List.of(new Task()));
        Mockito.when(taskRepository.getTasksByProjectProjectId(projectId)).thenReturn(taskListDto.getTasks());
        Assertions.assertEquals(taskListDto.getTasks(), taskService.getTaskList(projectId).getTasks());
        Mockito.verify(taskRepository).getTasksByProjectProjectId(projectId);
    }

    @Test
    void getSimpleTaskList() {
        Integer projectId = 1;
        List<TaskSimpleInfoDto> tasks  = List.of();
        Mockito.when(taskRepository.getSimpleTasksByProjectProjectId(projectId)).thenReturn(tasks);
        Assertions.assertEquals(tasks, taskService.getSimpleTaskList(projectId).getTasks());
        Mockito.verify(taskRepository).getSimpleTasksByProjectProjectId(projectId);
    }

    @Test
    void addTask() {
        taskService.addTask(1, "userId", new TaskUploadDto());
        Mockito.verify(taskRepository).save(Mockito.any());
    }

    @Test
    void updateTask_existTask(){
        Integer taskId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Task task = new Task();
        task.setUserId(userId);
        Mockito.doReturn(Optional.of(task)).when(taskRepository).findById(taskId);
        taskService.updateTask(taskId, userId, taskUploadDto);
        Mockito.verify(taskRepository).save(task);
    }

    @Test
    void updateTask_notExistTask_TaskNotFoundException(){
        Integer taskId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Mockito.doReturn(Optional.empty()).when(taskRepository).findById(taskId);
        Assertions.assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, userId, taskUploadDto));
    }

    @Test
    void updateTask_unAuthorizedException(){
        Integer taskId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Task task = new Task();
        task.setUserId("otherUserId");
        Mockito.doReturn(Optional.of(task)).when(taskRepository).findById(taskId);
        Assertions.assertThrows(UnAuthorizedException.class, () -> taskService.updateTask(taskId, userId, taskUploadDto));
    }

    @Test
    void deleteTask_authorized(){
        Integer taskId = 1;
        String userId = "userId";
        Task task = new Task();
        task.setUserId(userId);
        Mockito.doReturn(Optional.of(task)).when(taskRepository).findById(taskId);
        taskService.deleteTask(taskId, userId);
        Mockito.verify(taskRepository).delete(task);
    }

    @Test
    void deleteTask_unAuthorized(){
        Integer taskId = 1;
        String userId = "userId";
        Task task = new Task();
        task.setUserId("otherUserId");
        Mockito.doReturn(Optional.of(task)).when(taskRepository).findById(taskId);
        Assertions.assertThrows(UnAuthorizedException.class, () -> taskService.deleteTask(taskId, userId));
    }
}