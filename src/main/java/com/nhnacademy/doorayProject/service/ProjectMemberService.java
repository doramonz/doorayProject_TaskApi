package com.nhnacademy.doorayProject.service;

<<<<<<< HEAD
public interface ProjectMemberService {
    void deleteProjectMember(String slave, Integer projectId);

    public enum Auth {
        ADMIN, MEMBER
    }
    void addProjectMember(String  userId, Integer projectId, String auth);
    boolean checkAuth(String userId, Integer projectId);
=======
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

>>>>>>> origin/develop
}
