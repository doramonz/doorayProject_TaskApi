package com.nhnacademy.doorayProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.dto.TaskDto;
import com.nhnacademy.doorayProject.dto.TaskUpdateDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    Task task;


    @BeforeEach
    void setUp() {
        Project project = new Project();
        project.setProjectId(1);
        project.setName("213");
        project.setStatus("ok");

        task = new Task();
        task.setTaskContent("123");
        task.setTaskTitle("112");
        task.setCreateAt(LocalDateTime.now());
        task.setUserId("123");
        task.setStatus(Task.Status.할일);
        task.setProject(project);
    }

    @Test
    void testGetTask() {


        given(taskService.getTask(any(Integer.class))).willReturn(task);

        Task newTask = taskService.getTask(1);

        assertThat(newTask.getTaskTitle()).isEqualTo("112");
    }

    @Test
    void testGetTasks() {
        List<Task> list = Arrays.asList(task, new Task());
        given(taskService.getTaskList(any(Integer.class))).willReturn(list);

        List<Task> taskList = taskService.getTaskList(1);

        assertThat(taskList.size()).isEqualTo(2);
    }

    @Test
    void testSimpleGetTask() {
        List<SimpleTaskDto> list = Arrays.asList(new SimpleTaskDto(), new SimpleTaskDto());
        given(taskService.getSimpleTaskList(1)).willReturn(list);

        assertThat(taskService.getSimpleTaskList(1).size()).isEqualTo(2);
    }

    @Test
    void testPostTask() throws Exception {
        TaskDto dto = new TaskDto();
        dto.setTaskTile("234");
        dto.setTaskContent("wds");

        TaskUpdateDto updateDto = new TaskUpdateDto();
        updateDto.setTaskContent("wds");
        updateDto.setTaskTitle("34");
        updateDto.setUserId("234");

        given(taskService.postTask(any(Integer.class),any(String.class),any(TaskDto.class)))
                .willReturn(updateDto);

        assertThat(taskService.postTask(1,"213",dto)).isEqualTo(updateDto);
    }

    @Test
    void testUpdateTask() {
        TaskDto dto = new TaskDto();
        dto.setTaskTile("234");
        dto.setTaskContent("wds");

        TaskUpdateDto updateDto = new TaskUpdateDto();
        updateDto.setTaskContent("wds");
        updateDto.setTaskTitle("34");
        updateDto.setUserId("234");

        given(taskService.updateTask(any(Integer.class),any(String.class),any(TaskDto.class)))
                .willReturn(updateDto);

        assertThat(taskService.updateTask(1,"123",dto)).isEqualTo(updateDto);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskService).deleteTask(any(Integer.class),any(String.class));

        taskService.deleteTask(1,"123");

        verify(taskService,atLeastOnce()).deleteTask(1,"123");
    }





}
