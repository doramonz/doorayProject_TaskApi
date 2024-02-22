package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.repository.ProjectMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    void uploadProject() {


    }

    @Test
    void getProjectList() {
        ProjectDto projectDto = projectService.getProject(1);


        assertThat(projectDto.getName()).isEqualTo("1번");
    }




}