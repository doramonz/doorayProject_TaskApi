package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.SimpleTaskDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByProject_ProjectId(Integer projectId);

    List<SimpleTaskDto> findAllByProject_ProjectId(Integer projectId);


}
