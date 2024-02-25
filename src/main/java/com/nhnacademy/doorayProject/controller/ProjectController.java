package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.ProjectMemberDto;
import com.nhnacademy.doorayProject.dto.RequestProjectDto;
import com.nhnacademy.doorayProject.dto.UpdateResponseDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.ProjectMember;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.exeption.PermissionDeniedException;
import com.nhnacademy.doorayProject.exeption.ProjectIsNull;
import com.nhnacademy.doorayProject.exeption.UserProjectsNotFoundException;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import com.nhnacademy.doorayProject.service.ProjectMemberService;
import com.nhnacademy.doorayProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMemberRepository projectMemberRepository;
    @Autowired
    private ProjectMemberService memberService;

    public ProjectController(ProjectService projectService, ProjectMemberRepository projectMemberRepository) {
        this.projectService = projectService;
        this.projectMemberRepository = projectMemberRepository;
    }

    @PostMapping("/upload")
    public Project uploadProject(@RequestBody Project project) {
        if (Objects.isNull(project)) {
            throw new ProjectIsNull();
        }
        return projectService.uploadProject(project);
    }

    @GetMapping("/{userId}/list")
    public List<Project> getProejctList(@PathVariable String userId) {
        List<Project> list = projectService.getProjectList(userId);
        if (Objects.isNull(list)) {
            throw new UserProjectsNotFoundException();
        }
        return list;
    }

    @PutMapping("/{projectId}/update/{userId}")
    public UpdateResponseDto updateProject(@PathVariable Integer projectId, @RequestBody RequestProjectDto project, @PathVariable(name = "userId") String userId) {
        if (!memberService.adminCheck(userId, projectId)) {
            throw new PermissionDeniedException();
        }


        return projectService.updateProject(projectId, project, userId);
    }

    @DeleteMapping("/{projectId}/delete")
    public String deleteProejct(@PathVariable Integer projectId, @RequestBody User user) {

        if (memberService.adminCheck(user.getUserId(), projectId)) {
            projectService.deleteProject(projectId);
            return "삭제 성공";
        }
        return "삭제 실패";

    }

    @GetMapping("/{projectId}")
    public ProjectDto getProejct(@PathVariable Integer projectId) {
        return projectService.getProject(projectId);
    }

    @PostMapping("/{projectId}/addMember")
    public ProjectMemberDto addProjectMember(@PathVariable Integer projectId, @RequestBody ProjectMemberDto projectMemberDto) {
        return projectService.addMemeber(projectId, projectMemberDto);
    }

    @DeleteMapping("/{projectId}/deleteMember")
    public void deleteProjectMember(@PathVariable Integer projectId, @RequestBody ProjectMemberDto projectMemberDto) {
        projectService.deleteMember(projectId,projectMemberDto);
    }
}
