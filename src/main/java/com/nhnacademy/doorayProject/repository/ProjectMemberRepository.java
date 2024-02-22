package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,ProjectMember.Pk> {

    List<ProjectMember> findByPkUserId(String userId);


}
