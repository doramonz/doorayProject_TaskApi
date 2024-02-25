package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;

public interface TaskService {
    TaskInfoDto getTaskInfo(Integer taskId);
}
