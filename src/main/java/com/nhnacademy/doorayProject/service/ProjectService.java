package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.dto.project.ProjectListDto;
import com.nhnacademy.doorayProject.dto.project.ProjectMemberDto;

public interface ProjectService {
    ProjectInfoDto addProject(String userId, ProjectInfoDto projectInfoDto);

    ProjectListDto getProjects(String userId);

    ProjectInfoDto updateProject(String userId, Integer projectId, ProjectInfoDto projectInfoDto);

    void deleteProject(String userId, Integer projectId);

    ProjectInfoDto getProject(Integer projectId);

    void addProjectMemeber(Integer projectId, ProjectMemberDto projectMemberDto);

    void deleteProjectMember(Integer projectId, ProjectMemberDto projectMemberDto);
}
