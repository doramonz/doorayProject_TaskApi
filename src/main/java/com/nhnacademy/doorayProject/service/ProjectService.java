package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.ProjectMemberDto;
import com.nhnacademy.doorayProject.dto.RequestProjectDto;
import com.nhnacademy.doorayProject.dto.UpdateResponseDto;
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


    public UpdateResponseDto updateProject(Integer projectId, RequestProjectDto editProject,String userId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (Objects.isNull(project)) {
            throw new NullPointerException();
        }

        project.setName(editProject.getName());
        project.setStatus(editProject.getStatus());

        projectRepository.save(project);
        UpdateResponseDto responseDto = new UpdateResponseDto();
        responseDto.setUserId(userId);
        responseDto.setName(editProject.getName());

        return responseDto;
    }

    public boolean deleteProject(Integer projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (Objects.isNull(project.get())) {
            return false;
        }
        projectRepository.deleteById(projectId);
        return true;
    }

    public ProjectDto getProject(Integer projectId) {
        ProjectDto project = projectRepository.findByProjectId(projectId);

        return project;
    }

    public ProjectMemberDto addMemeber(Integer projectId, ProjectMemberDto dto) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByPkProjectId(projectId);
        for (ProjectMember member : projectMembers) {
            if (member.getPk().getUserId().equals(dto.getMaster())) {
                ProjectMember.Pk pk = new ProjectMember.Pk(dto.getSlave(), projectId);
                ProjectMember newMember = new ProjectMember();
                newMember.setPk(pk);
                newMember.setProject(member.getProject());
                newMember.setAuth("member");
                projectMemberRepository.save(newMember);
                return dto;
            }
        }
        return null;
    }

    public void deleteMember(Integer projectId, ProjectMemberDto dto) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByPkProjectId(projectId);

        for (ProjectMember member : projectMembers) {
                if (member.getPk().getUserId().equals(dto.getSlave())) {
                    if (member.getAuth().equals("member")) {
                        projectMemberRepository.deleteById(member.getPk());
                    }
                }

        }
    }

}
