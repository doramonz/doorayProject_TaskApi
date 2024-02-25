package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.dto.TaskDto;
import com.nhnacademy.doorayProject.dto.TaskUpdateDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import com.nhnacademy.doorayProject.exeption.NotFoundProjectException;
import com.nhnacademy.doorayProject.exeption.NotFoundTaskException;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import com.nhnacademy.doorayProject.repository.TaskRepository;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private  final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    @Override
    public Task getTask(Integer taskId) {
        Optional<Task> optionalTask;
        if((optionalTask = taskRepository.findById(taskId)).isEmpty()) {
            throw new NotFoundTaskException();
        }
        return optionalTask.get();
    }

    @Override
    public List<Task> getTaskList(Integer projectId) {
        // todo projectId를 가진 project가 있는지 확인
        if(projectRepository.findById(projectId).isEmpty()) {
            throw new NotFoundProjectException();
        }

        return taskRepository.findByProject_ProjectId(projectId);
    }

    @Override
    public List<SimpleTaskDto> getSimpleTaskList(Integer projectId) {
        if(projectRepository.findById(projectId).isEmpty()) {
            throw new NotFoundProjectException();
        }
        return taskRepository.findAllByProject_ProjectId(projectId);
    }

    @Override
    public TaskUpdateDto postTask(Integer projectId, String userId, TaskDto taskDto) {
        Optional<Project> project;
        if((project = projectRepository.findById(projectId)).isEmpty()) {
            throw new NotFoundProjectException();
        }

        Task task = new Task();
        task.setUserId(userId);
        task.setTaskTitle(taskDto.getTaskTile());
        task.setTaskContent(taskDto.getTaskContent());
        task.setCreateAt(LocalDateTime.now());
        task.setProject(project.get());
        Task newTask = taskRepository.save(task);

        return new TaskUpdateDto(newTask.getUserId(), newTask.getTaskTitle(), newTask.getTaskContent());
    }

    @Override
    public TaskUpdateDto updateTask(Integer taskId, String userId, TaskDto taskDto) {
        Optional<Task> optionalTask;
        if((optionalTask= taskRepository.findById(taskId)).isEmpty()) {
            throw new NotFoundTaskException();
        }
        Task newTask = optionalTask.get();
        if(!Objects.equals(newTask.getUserId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "다른 유저입니다");
        }
        newTask.setUserId(userId);
        newTask.setTaskTitle(taskDto.getTaskTile());
        newTask.setTaskContent(taskDto.getTaskContent());

        Task updateTask = taskRepository.save(newTask);
        return new TaskUpdateDto(updateTask.getUserId(), updateTask.getTaskTitle(), updateTask.getTaskContent());
    }

    @Override
    public void deleteTask(Integer taskId, String userId) {
        Optional<Task> optionalTask;
        if((optionalTask= taskRepository.findById(taskId)).isEmpty()) {
            throw new NotFoundTaskException();
        }
        Task newTask = optionalTask.get();
        if(!Objects.equals(newTask.getUserId(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "다른 유저입니다");
        }
        taskRepository.delete(newTask);
    }


}
