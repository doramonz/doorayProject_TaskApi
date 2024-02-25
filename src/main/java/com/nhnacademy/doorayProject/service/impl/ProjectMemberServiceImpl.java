package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.ProjectMember;
import com.nhnacademy.doorayProject.exeption.ProjectMemberNotFoundException;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import com.nhnacademy.doorayProject.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("projectMemberService")
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public void deleteProjectMember(String slave, Integer projectId) {
        projectMemberRepository.deleteById(new ProjectMember.Pk(slave, projectId));
    }

    @Override
    public void addProjectMember(String userId, Integer projectId, String auth) {
        projectMemberRepository.save(new ProjectMember(new ProjectMember.Pk(userId, null),auth, new Project(projectId,null,null)));
    }

    @Override
    public boolean checkAuth(String userId, Integer projectId) {
        ProjectMember projectMember = projectMemberRepository.findById(new ProjectMember.Pk(userId, projectId)).orElseThrow(ProjectMemberNotFoundException::new);
        return projectMember.getAuth().equals(Auth.ADMIN.name());
    }
}
