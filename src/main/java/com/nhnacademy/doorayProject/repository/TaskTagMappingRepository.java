package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.TaskTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagMappingRepository extends JpaRepository<TaskTagMapping,TaskTagMapping.Pk> {
}
