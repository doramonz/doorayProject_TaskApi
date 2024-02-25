package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@DataJpaTest
@Transactional
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;



    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {


    }


    @Test

    void uploadProject() {

        Project a = new Project(1, "1", "oo");
        entityManager.merge(a);

        Optional<Project> actual =projectRepository.findById(1);

        assertThat(actual.get()).isEqualTo(a);

    }

    @Test
    void getProjectList() {
        Project a = new Project(1, "1", "oo");
        Project b = new Project(2, "2", "oo");
        Project c = new Project(3, "3", "oo");
        Project d = new Project(4, "4", "oo");
        entityManager.merge(a);
        entityManager.merge(b);
        entityManager.merge(c);
        entityManager.merge(d);
        List<Project> actual = new ArrayList<>();
        actual.add(a);
        actual.add(b);
        actual.add(c);
        actual.add(d);
        List<Project> projects = projectRepository.findAll();
        assertThat(actual).isEqualTo(projects);
    }

}