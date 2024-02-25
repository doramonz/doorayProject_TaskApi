package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.dto.TaskDto;
import com.nhnacademy.doorayProject.dto.TaskUpdateDto;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.service.TaskService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void testGetTask() throws Exception {

        given(taskService.getTask(any(Integer.class))).willReturn(any(Task.class));

        mockMvc.perform(get("/projects/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(taskService,atLeastOnce()).getTask(1);
    }

    @Test
    void testGetTaskList() throws Exception {
        given(taskService.getTaskList(any(Integer.class))).willReturn(Arrays.asList(new Task(), new Task()));

        mockMvc.perform(get("/projects/1/tasks/list")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(taskService, atLeastOnce()).getTaskList(1);
    }

    @Test
    void testGetSimpleTaskList() throws Exception {
        List<SimpleTaskDto> simpleTaskDtos = Arrays.asList(new SimpleTaskDto(1,"12","qwe"), new SimpleTaskDto(1,"13","qwer"));
        when(taskService.getSimpleTaskList(any(Integer.class)))
                .thenReturn(simpleTaskDtos);

        mockMvc.perform(get("/projects/1/tasks/list/simple")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(taskService, atLeastOnce()).getSimpleTaskList(1);
    }

//    @Test
//    void test

    @Test
    void testAddTask() throws Exception {
        TaskDto taskDto = new TaskDto("1","qwer");
        TaskUpdateDto taskUpdateDto = new TaskUpdateDto("1","123","qwer");
        given(taskService.postTask(any(Integer.class),any(String.class),any(TaskDto.class)))
                .willReturn(taskUpdateDto);

        mockMvc.perform(post("/projects/1/tasks/upload/1")
                .content(new ObjectMapper().writeValueAsString(taskDto))
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void testUpdatetask() throws Exception{

        TaskDto taskDto = new TaskDto("1","qwer");
        TaskUpdateDto taskUpdateDto = new TaskUpdateDto("1","wow","qwer");

        when(taskService.updateTask(any(Integer.class),any(String.class),any(TaskDto.class)))
                .thenReturn(taskUpdateDto);

        mockMvc.perform(put("/projects/tasks/1/update/1")
                .content(new ObjectMapper().writeValueAsString(taskDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(any(Integer.class),any(String.class));

        mockMvc.perform(delete("/projects/tasks/1/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(taskService, atLeastOnce()).deleteTask(1,"1");
    }
}
