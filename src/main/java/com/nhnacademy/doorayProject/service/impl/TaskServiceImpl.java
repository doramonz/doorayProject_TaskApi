package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.repository.TaskRepository;
import com.nhnacademy.doorayProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
}