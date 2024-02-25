package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class TagRepositoryTest {


    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void findByProjectId() {
        ProjectDto project =  projectRepository.findByProjectId(1);
        Project project1 = new Project();
        project1.setProjectId(1);
        project1.setName(project.getName());
        project1.setStatus(project.getStatus());

        List<Tag> tags = tagRepository.findByProjectId(project1);

        log.info(tags.toString());

    }
}