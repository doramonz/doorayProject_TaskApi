package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.entity.ProjectMember;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository repository;

    public ProjectMemberService(ProjectMemberRepository projectMemberRepository, ProjectRepository repository) {
        this.projectMemberRepository = projectMemberRepository;
        this.repository = repository;
    }

    public boolean adminCheck(String userId, Integer projectId) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByPkUserId(userId);

        for (ProjectMember member : projectMembers) {
            if (member.getPk().getProjectId().equals(projectId) && member.getAuth().equals("admin")) {
                return true;
            }
        }
        return false;
    }

}
