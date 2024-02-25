package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.dto.project.ProjectListDto;
import com.nhnacademy.doorayProject.dto.project.ProjectMemberDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.exeption.ProjectNotFoundException;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import com.nhnacademy.doorayProject.service.ProjectMemberService;
import com.nhnacademy.doorayProject.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberService projectMemberService;

    @Override
    public ProjectInfoDto addProject(String userId, ProjectInfoDto projectInfoDto) {
        Project project =  projectRepository.save(new Project(null,projectInfoDto.getName(),projectInfoDto.getStatus()));
        projectMemberService.addProjectMember(userId,project.getProjectId(), ProjectMemberService.Auth.ADMIN.name());
        return new ProjectInfoDto(project.getName(),project.getStatus());
    }

    @Override
    public ProjectListDto getProjects(String userId) {
        List<Project> projects = projectRepository.getProjectBy(userId);
        return new ProjectListDto(projects);
    }

    @Override
    public ProjectInfoDto updateProject(String userId, Integer projectId, ProjectInfoDto projectInfoDto) {
        if(!projectMemberService.checkAuth(userId,projectId)){
            throw new UnAuthorizedException();
        }
        Project project = new Project(projectId,projectInfoDto.getName(),projectInfoDto.getStatus());
        project = projectRepository.save(project);
        return new ProjectInfoDto(project.getName(),project.getStatus());
    }

    @Override
    public void deleteProject(String userId, Integer projectId) {
        if(!projectMemberService.checkAuth(userId,projectId)){
            throw new UnAuthorizedException();
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectInfoDto getProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        return new ProjectInfoDto(project.getName(),project.getStatus());
    }

    @Override
    public void addProjectMemeber(Integer projectId, ProjectMemberDto projectMemberDto) {
        if(!projectMemberService.checkAuth(projectMemberDto.getMaster(),projectId)){
            throw new UnAuthorizedException();
        }
        projectMemberService.addProjectMember(projectMemberDto.getSlave(),projectId,ProjectMemberService.Auth.MEMBER.name());
    }

    @Override
    public void deleteProjectMember(Integer projectId, ProjectMemberDto projectMemberDto) {
        if(!projectMemberService.checkAuth(projectMemberDto.getMaster(),projectId)){
            throw new UnAuthorizedException();
        }
        projectMemberService.deleteProjectMember(projectMemberDto.getSlave(),projectId);
    }
}
