package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk>{
=======
import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,ProjectMember.Pk> {

    List<ProjectMember> findByPkUserId(String userId);

    List<ProjectMember> findByPkProjectId(Integer projectId);


>>>>>>> origin/develop
}
