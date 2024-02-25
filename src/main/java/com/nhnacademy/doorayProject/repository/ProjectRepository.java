package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select new com.nhnacademy.doorayProject.entity.Project(p.projectId, p.name, p.status) from Project p join ProjectMember pm on p.projectId = pm.project.projectId where pm.pk.userId = :userId")
    List<Project> getProjectBy(String userId);

}