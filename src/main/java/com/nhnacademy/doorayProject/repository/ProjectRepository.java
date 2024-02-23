package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    ProjectDto findByProjectId(Integer projectId);

}
