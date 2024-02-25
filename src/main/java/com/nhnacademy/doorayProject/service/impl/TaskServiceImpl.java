package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskListDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoListDto;
import com.nhnacademy.doorayProject.dto.task.TaskUploadDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.TaskNotFoundException;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.repository.TaskRepository;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public TaskInfoDto getTaskInfo(Integer taskId) {
        return taskRepository.getTaskInfo(taskId);
    }

    @Override
    public TaskListDto getTaskList(Integer projectId) {
        return new TaskListDto(taskRepository.getTasksByProjectProjectId(projectId));
    }

    @Override
    public TaskSimpleInfoListDto getSimpleTaskList(Integer projectId) {
        return new TaskSimpleInfoListDto(taskRepository.getSimpleTasksByProjectProjectId(projectId));
    }

    @Override
    public void addTask(Integer projectId, String userId, TaskUploadDto taskUploadDto) {
        Task task = new Task(null, userId,null, taskUploadDto.getTaskTitle(),taskUploadDto.getTaskContent(), Task.Status.할일, LocalDateTime.now(), taskUploadDto.getEndDate(), new Project(projectId,null,null));
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Integer taskId, String userId, TaskUploadDto taskUploadDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if(!task.getUserId().equals(userId)) {
            throw new UnAuthorizedException();
        }
        task.setTaskTitle(taskUploadDto.getTaskTitle());
        task.setTaskContent(taskUploadDto.getTaskContent());
        task.setEndDate(taskUploadDto.getEndDate());
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer taskId, String userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if(!task.getUserId().equals(userId)) {
            throw new UnAuthorizedException();
        }
        taskRepository.delete(task);
    }
}
