package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
