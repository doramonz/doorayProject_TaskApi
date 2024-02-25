package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskListDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoListDto;
import com.nhnacademy.doorayProject.dto.task.TaskUploadDto;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskRestController.class)
@AutoConfigureMockMvc
class TaskRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void getTask() throws Exception {
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        Integer taskId = 1;
        Mockito.when(taskService.getTaskInfo(taskId)).thenReturn(taskInfoDto);
        mockMvc.perform(get("/projects/tasks/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(taskInfoDto)));
    }

    @Test
    void getTaskList() throws Exception {
        Integer projectId = 1;
        TaskListDto taskListDto = new TaskListDto(List.of());
        Mockito.when(taskService.getTaskList(projectId)).thenReturn(taskListDto);
        mockMvc.perform(get("/projects/{projectId}/tasks/list", projectId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(taskListDto)));
    }

    @Test
    void getSimpleTaskList() throws Exception {
        Integer projectId = 1;
        TaskSimpleInfoListDto taskSimpleInfoListDto = new TaskSimpleInfoListDto(List.of());
        Mockito.when(taskService.getSimpleTaskList(projectId)).thenReturn(taskSimpleInfoListDto);
        mockMvc.perform(get("/projects/{projectId}/tasks/list/simple", projectId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(taskSimpleInfoListDto)));
    }

    @Test
    void addTask() throws Exception {
        Integer projectId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Mockito.doNothing().when(taskService).addTask(projectId, userId, taskUploadDto);
        mockMvc.perform(post("/projects/{projectId}/tasks/upload/{userId}", projectId, userId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(taskUploadDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateTask_authorized() throws Exception {
        Integer taskId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Mockito.doNothing().when(taskService).updateTask(taskId, userId, taskUploadDto);
        mockMvc.perform(put("/projects/tasks/{taskId}/update/{userId}", taskId, userId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(taskUploadDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateTask_unAuthorized() throws Exception {
        Integer taskId = 1;
        String userId = "userId";
        TaskUploadDto taskUploadDto = new TaskUploadDto();
        Mockito.doThrow(UnAuthorizedException.class).when(taskService).updateTask(any(), any(), any());
        mockMvc.perform(put("/projects/tasks/{taskId}/update/{userId}", taskId, userId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(taskUploadDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteTask_authorized() throws Exception {
        Integer taskId = 1;
        String userId = "userId";
        Mockito.doNothing().when(taskService).deleteTask(taskId, userId);
        mockMvc.perform(delete("/projects/tasks/{taskId}/delete/{userId}", taskId, userId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTask_unAuthorized() throws Exception {
        Integer taskId = 1;
        String userId = "userId";
        Mockito.doThrow(UnAuthorizedException.class).when(taskService).deleteTask(any(), any());
        mockMvc.perform(delete("/projects/tasks/{taskId}/delete/{userId}", taskId, userId))
                .andExpect(status().isUnauthorized());
    }
}