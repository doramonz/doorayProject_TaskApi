package com.nhnacademy.doorayProject.service;

<<<<<<< HEAD
import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskListDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoListDto;
import com.nhnacademy.doorayProject.dto.task.TaskUploadDto;

public interface TaskService {
    TaskInfoDto getTaskInfo(Integer taskId);

    TaskListDto getTaskList(Integer projectId);

    TaskSimpleInfoListDto getSimpleTaskList(Integer projectId);

    void addTask(Integer projectId, String userId, TaskUploadDto taskUploadDto);

    void updateTask(Integer taskId, String userId, TaskUploadDto taskUploadDto);

    void deleteTask(Integer taskId, String userId);
=======
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
>>>>>>> origin/develop
}
