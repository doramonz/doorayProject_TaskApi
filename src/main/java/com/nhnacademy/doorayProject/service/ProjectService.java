package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.RequestProjectDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.ProjectMember;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    //저장할때는 entity사용 해야한다
    public Project uploadProject(Project project) {
        if (Objects.isNull(project)) {
            throw new IllegalArgumentException();
        }
        return projectRepository.save(project);
    }

    public List<Project> getProjectList(String userId) {
        List<ProjectMember> projectMember = projectMemberRepository.findByPkUserId(userId);
        List<Project> projects = new ArrayList<>();
        for (ProjectMember member : projectMember) {
            projects.add(member.getProject());
        }
        return projects;
    }


    public RequestProjectDto updateProject(Integer projectId, RequestProjectDto editProject) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (Objects.isNull(project)) {
            throw new NullPointerException();
        }
        project.setName(editProject.name());
        project.setStatus(editProject.getStatus());

        projectRepository.save(project);

        return editProject;
    }

    public void deleteProject(Integer projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (Objects.isNull(project.get())) {
            throw new NullPointerException();
        }
        projectRepository.deleteById(projectId);

    }

    public ProjectDto getProject(Integer projectId) {
        ProjectDto project = projectRepository.findByProjectId(projectId);

        return project;
    }

}
