package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.TaskTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTagMappingRepository extends JpaRepository<TaskTagMapping,TaskTagMapping.Pk> {
}
