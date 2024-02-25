package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.project.ProjectInfoDto;
import com.nhnacademy.doorayProject.dto.project.ProjectListDto;
import com.nhnacademy.doorayProject.dto.project.ProjectMemberDto;
import com.nhnacademy.doorayProject.exeption.UnAuthorizedException;
import com.nhnacademy.doorayProject.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ProjectRestController {
    private final ProjectService projectService;

    @PostMapping("/{userId}/upload")
    public ResponseEntity<Void> addProject(@PathVariable("userId") String userId,@RequestBody ProjectInfoDto projectInfoDto) {
        ResponseEntity<Void> responseEntity;
        try {
            projectService.addProject(userId, projectInfoDto);
            responseEntity = ResponseEntity.created(null).build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<ProjectListDto> getProjectList(@PathVariable("userId") String userId) {
        ResponseEntity<ProjectListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(projectService.getProjects(userId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping("/{projectId}/update/{userId}")
    public ResponseEntity<ProjectInfoDto> updateProject(@PathVariable("projectId") Integer projectId, @PathVariable("userId") String userId, @RequestBody ProjectInfoDto projectInfoDto) {
        ResponseEntity<ProjectInfoDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(projectService.updateProject(userId, projectId, projectInfoDto));
        } catch (Exception e) {
            if(e instanceof UnAuthorizedException)
                responseEntity = ResponseEntity.status(401).build();
            else
                responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{projectId}/delete/{userId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Integer projectId, @PathVariable("userId") String userId) {
        ResponseEntity<Void> responseEntity;
        try {
            projectService.deleteProject(userId, projectId);
            responseEntity = ResponseEntity.noContent().build();
        } catch (Exception e) {
            if(e instanceof UnAuthorizedException)
                responseEntity = ResponseEntity.status(401).build();
            else
                responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectInfoDto> getProject(@PathVariable("projectId") Integer projectId) {
        ResponseEntity<ProjectInfoDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(projectService.getProject(projectId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PostMapping("/{projectId}/addMember")
    public ResponseEntity<Void> addMember(@PathVariable("projectId") Integer projectId, @RequestBody ProjectMemberDto projectMemberDto) {
        ResponseEntity<Void> responseEntity;
        try {
            projectService.addProjectMemeber(projectId, projectMemberDto);
            responseEntity = ResponseEntity.created(null).build();
        } catch (Exception e) {
            if (e instanceof UnAuthorizedException)
                responseEntity = ResponseEntity.status(401).build();
            else
                responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{projectId}/deleteMember")
    public ResponseEntity<Void> deleteMember(@PathVariable("projectId") Integer projectId, @RequestBody ProjectMemberDto projectMemberDto) {
        ResponseEntity<Void> responseEntity;
        try {
            projectService.deleteProjectMember(projectId, projectMemberDto);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e instanceof UnAuthorizedException)
                responseEntity = ResponseEntity.status(401).build();
            else
                responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }
}
