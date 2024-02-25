package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk>{
}
