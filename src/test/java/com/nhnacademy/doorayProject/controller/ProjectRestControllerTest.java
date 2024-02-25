package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.dto.project.ProjectListDto;
import com.nhnacademy.doorayProject.dto.project.ProjectMemberDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectRestController.class)
@AutoConfigureMockMvc
class ProjectRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void addProject() throws Exception {
        String userId = "userId";
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Mockito.when(projectService.addProject(userId, projectInfoDto)).thenReturn(projectInfoDto);
        mockMvc.perform(post("/projects/{userId}/upload", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectInfoDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getProjectList() throws Exception {
        ProjectListDto projectListDto = new ProjectListDto(List.of(new Project(1, "projectName1", "projectStatus1"), new Project(2, "projectName2", "projectStatus2")));
        String userId = "userId";
        Mockito.when(projectService.getProjects(userId)).thenReturn(projectListDto);
        mockMvc.perform(get("/projects/{userId}/list", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(projectListDto)));
    }

    @Test
    void updateProject_authorize() throws Exception {
        String userId = "userId";
        Integer projectId = 1;
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Mockito.when(projectService.updateProject(userId, projectId, projectInfoDto)).thenReturn(projectInfoDto);
        mockMvc.perform(put("/projects/{projectId}/update/{userId}", projectId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectInfoDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateProject_unauthorized() throws Exception {
        String userId = "userId";
        Integer projectId = 1;
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Mockito.when(projectService.updateProject(any(), any(), any())).thenThrow(UnAuthorizedException.class);
        mockMvc.perform(put("/projects/{projectId}/update/{userId}", projectId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectInfoDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteProject_authorize() throws Exception {
        String userId = "userId";
        Integer projectId = 1;
        Mockito.doNothing().when(projectService).deleteProject(userId, projectId);
        mockMvc.perform(delete("/projects/{projectId}/delete/{userId}", projectId, userId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProject_unauthorized() throws Exception {
        String userId = "userId";
        Integer projectId = 1;
        Mockito.doThrow(UnAuthorizedException.class).when(projectService).deleteProject(userId, projectId);
        mockMvc.perform(delete("/projects/{projectId}/delete/{userId}", projectId, userId))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getProject() throws Exception {
        ProjectInfoDto projectInfoDto = new ProjectInfoDto("projectName", "projectStatus");
        Integer projectId = 1;
        Mockito.when(projectService.getProject(projectId)).thenReturn(projectInfoDto);
        mockMvc.perform(get("/projects/{projectId}", projectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(projectInfoDto)));
    }

    @Test
    void addMember_authorize() throws Exception {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.doNothing().when(projectService).addProjectMemeber(projectId, projectMemberDto);
        mockMvc.perform(post("/projects/{projectId}/addMember", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void addMember_unauthorized() throws Exception {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.doThrow(UnAuthorizedException.class).when(projectService).addProjectMemeber(projectId, projectMemberDto);
        mockMvc.perform(post("/projects/{projectId}/addMember", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberDto)))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void deleteMember_authorize() throws Exception {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.doNothing().when(projectService).deleteProjectMember(projectId, projectMemberDto);
        mockMvc.perform(delete("/projects/{projectId}/deleteMember", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMember_unauthorized() throws Exception {
        Integer projectId = 1;
        ProjectMemberDto projectMemberDto = new ProjectMemberDto("master", "slave");
        Mockito.doThrow(UnAuthorizedException.class).when(projectService).deleteProjectMember(any(), any());
        mockMvc.perform(delete("/projects/{projectId}/deleteMember", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberDto)))
                .andExpect(status().isUnauthorized());
    }
}