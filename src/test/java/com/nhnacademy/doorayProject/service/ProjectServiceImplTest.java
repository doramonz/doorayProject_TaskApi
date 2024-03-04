package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.dto.project.ProjectMemberDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import com.nhnacademy.doorayProject.service.ProjectMemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberService projectMemberService;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void addProject() {
        Project input = new Project(null, "projectName", "projectStatus");
        Project output = new Project(1, "projectName", "projectStatus");
        Mockito.when(projectRepository.save(input)).thenReturn(output);
        ProjectInfoDto projectInfoDto = projectService.addProject("userId", new ProjectInfoDto(input.getName(), input.getStatus()));
        Assertions.assertAll(
                () -> Assertions.assertEquals(output.getName(), projectInfoDto.getName()),
                () -> Assertions.assertEquals(output.getStatus(), projectInfoDto.getStatus())
        );
    }

    @Test
    void updateProject_authorize() {
        String userId = "userId";
        Integer projectId = 1;
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Project project = new Project(projectId, projectInfoDto.getName(), projectInfoDto.getStatus());
        Mockito.when(projectMemberService.checkAuth(userId, projectId)).thenReturn(true);
        Mockito.when(projectRepository.save(project)).thenReturn(project);
        ProjectInfoDto result = projectService.updateProject(userId, projectId, projectInfoDto);
        Assertions.assertAll(
                () -> Assertions.assertEquals(projectInfoDto.getName(), result.getName()),
                () -> Assertions.assertEquals(projectInfoDto.getStatus(), result.getStatus())
        );
    }

    @Test
    void updateProject_unauthorized() {
        String userId = "userId";
        Integer projectId = 1;
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Mockito.when(projectMemberService.checkAuth(userId, projectId)).thenReturn(false);
        Assertions.assertThrows(UnAuthorizedException.class, () -> projectService.updateProject(userId, projectId, projectInfoDto));
    }

    @Test
    void deleteProject_authorize() {
        String userId = "userId";
        Integer projectId = 1;
        Mockito.when(projectMemberService.checkAuth(userId, projectId)).thenReturn(true);
        projectService.deleteProject(userId, projectId);
        Mockito.verify(projectRepository).deleteById(projectId);
    }

    @Test
    void deleteProject_unauthorized() {
        String userId = "userId";
        Integer projectId = 1;
        Mockito.when(projectMemberService.checkAuth(userId, projectId)).thenReturn(false);
        Assertions.assertThrows(UnAuthorizedException.class, () -> projectService.deleteProject(userId, projectId));
    }

    @Test
    void getProject() {
        String userId = "userId";
        List<Project> projects = List.of(
                new Project(1, "projectName1", "projectStatus1"),
                new Project(2, "projectName2", "projectStatus2")
        );
        Mockito.when(projectRepository.getProjectBy(userId)).thenReturn(projects);
        Assertions.assertEquals(projects, projectRepository.getProjectBy(userId));
    }

    @Test
    void addProjectMemeber_authorize() {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.when(projectMemberService.checkAuth(projectMemberDto.getMaster(), projectId)).thenReturn(true);
        projectService.addProjectMemeber(projectId, projectMemberDto);
    }

    @Test
    void addProjectMemeber_unauthorized() {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.when(projectMemberService.checkAuth(projectMemberDto.getMaster(), projectId)).thenReturn(false);
        Assertions.assertThrows(UnAuthorizedException.class, () -> projectService.addProjectMemeber(projectId, projectMemberDto));
    }

    @Test
    void deleteProjectMember_authorize() {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.when(projectMemberService.checkAuth(projectMemberDto.getMaster(), projectId)).thenReturn(true);
        projectService.deleteProjectMember(projectId, projectMemberDto);
    }

    @Test
    void deleteProjectMember_unauthorized() {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.when(projectMemberService.checkAuth(projectMemberDto.getMaster(), projectId)).thenReturn(false);
        Assertions.assertThrows(UnAuthorizedException.class, () -> projectService.deleteProjectMember(projectId, projectMemberDto));
    }
}