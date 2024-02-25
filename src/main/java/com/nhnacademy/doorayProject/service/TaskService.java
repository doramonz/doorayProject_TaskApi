package com.nhnacademy.doorayProject.service;

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
}
