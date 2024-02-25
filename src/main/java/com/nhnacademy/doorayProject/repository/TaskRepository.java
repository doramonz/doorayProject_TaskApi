package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.task.TaskInfoDto;
import com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoDto;
import com.nhnacademy.doorayProject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("select new com.nhnacademy.doorayProject.dto.task.TaskInfoDto(t.taskTitle, t.taskContent, t.userId, t.status, t.createAt, t.endDate, t.mileStoneId) from Task t where t.taskId = ?1")
    TaskInfoDto getTaskInfo(Integer taskId);

    List<Task> getTasksByProjectProjectId(Integer projectId);

    @Query("select new com.nhnacademy.doorayProject.dto.task.TaskSimpleInfoDto(t.taskId ,t.taskTitle, t.userId, t.status) from Task t where t.project.projectId = ?1")
    List<TaskSimpleInfoDto> getSimpleTasksByProjectProjectId(Integer projectId);
}
