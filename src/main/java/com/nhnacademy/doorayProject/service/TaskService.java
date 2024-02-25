package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.dto.TaskDto;
import com.nhnacademy.doorayProject.dto.TaskUpdateDto;
import com.nhnacademy.doorayProject.entity.Task;

import java.util.List;

public interface TaskService {
    Task getTask(Integer taskId);

    List<Task> getTaskList(Integer projectId);

    List<SimpleTaskDto> getSimpleTaskList(Integer projectId);

    TaskUpdateDto updateTask(Integer taskId, String userId, TaskDto taskDto);

    void deleteTask(Integer taskId, String userId);

    TaskUpdateDto postTask(Integer projectId, String userId, TaskDto taskDto);
}
