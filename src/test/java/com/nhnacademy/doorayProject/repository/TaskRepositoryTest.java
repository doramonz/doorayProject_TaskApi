package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class TaskRepositoryTest {
    @Autowired
    TaskRepository taskRepository;

    @Test
    void findByProject() {
        List<Task> taskList = taskRepository.findByProject_ProjectId(1);
        for(Task task : taskList) {
            log.info("--------------------------------------------------------------");
        log.info("{}", task.toString());
            log.info("--------------------------------------------------------------");

        }
    }

    @Test
    void testGetByProject_ProjectId() {
        List<SimpleTaskDto> result = taskRepository.findAllByProject_ProjectId(1);

        for(SimpleTaskDto simpleTaskDto : result) {
            log.info("--------------------------------------------------------------");
            log.info("{}, {}, {}", simpleTaskDto.getUserId(), simpleTaskDto.getTaskTitle(), simpleTaskDto.getTaskId());
        }
    }
}