package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.ProjectMemberDto;
import com.nhnacademy.doorayProject.dto.RequestProjectDto;
import com.nhnacademy.doorayProject.dto.UpdateResponseDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.ProjectMember;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@SpringBootTest
@Transactional
class ProjectServiceTest {






    @Autowired
    private ProjectMemberRepository projectMemberRepository;
    @Autowired
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {



    }


    @Test
    void uploadProjectTest() {
        Project project = projectService.uploadProject(new Project(11,"upload", "보류"));
        ProjectDto actual = projectService.getProject(11);
        assertThat(actual.getName()).isEqualTo("upload");
        assertThat(actual.getStatus()).isEqualTo("보류");

    }

    @Test
    void getProjectList() {
        List<Project> projects = projectService.getProjectList("test1");
        List<Project> actual = List.of(new Project(1,"1번","할일"),new Project(2,"2번","할일"),new Project(3,"3번","할일"));
        for (int i = 0; i < projects.size(); i++) {

            assertThat(actual.get(i)).isEqualTo(projects.get(i));
        }


    }

    @Test
    void getProjectTest() {
        ProjectDto projectDto = projectService.getProject(10);

        assertThat(projectDto.getName()).isEqualTo("upload");
        assertThat(projectDto.getStatus()).isEqualTo("보류");

    }

    @Test
    void deleteProjectTest() {
        projectService.deleteProject(10);
        Assertions.assertNull(projectService.getProject(10));

    }

    @Test
    void updateTest() {
        RequestProjectDto requestProjectDto = new RequestProjectDto();
        requestProjectDto.setUserId("test");
        requestProjectDto.setName("testUpdate");
        requestProjectDto.setStatus("할일");

      UpdateResponseDto responseDto = projectService.updateProject(10, requestProjectDto);
        ProjectDto project = projectService.getProject(10);

        assertThat(responseDto.getUserId()).isEqualTo("test");
        assertThat(responseDto.getName()).isEqualTo("testUpdate");

        assertThat(project.getName()).isEqualTo("testUpdate");
        assertThat(project.getStatus()).isEqualTo("할일");

    }

    @Test
    void addMemberTest() {
        ProjectMemberDto memberDto = new ProjectMemberDto();
        memberDto.setMaster("test1");
        memberDto.setSlave("test3");

        ProjectMemberDto members = projectService.addMemeber(1, memberDto);

        assertThat(memberDto).isEqualTo(members);

        List<ProjectMember> projectIdMember = projectMemberRepository.findByPkProjectId(1);

        for (ProjectMember member : projectIdMember) {
            log.info(String.valueOf(member));
        }

    }

    @Test
    void deleteMemberTest() {
        ProjectMemberDto memberDto = new ProjectMemberDto();
        memberDto.setMaster("test1");
        memberDto.setSlave("test3");

        ProjectMemberDto members = projectService.addMemeber(1, memberDto);

        assertThat(memberDto).isEqualTo(members);

        projectService.deleteMember(1, memberDto);

        List<ProjectMember> projectMembers = projectMemberRepository.findByPkProjectId(1);
        for (ProjectMember member : projectMembers) {
            log.info(String.valueOf(member));
        }
    }

}