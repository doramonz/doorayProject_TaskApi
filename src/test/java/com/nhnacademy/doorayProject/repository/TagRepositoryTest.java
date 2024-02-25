package com.nhnacademy.doorayProject.repository;

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

    @Test
    void findByProjectId() {
        List<Tag> tags = tagRepository.findByProjectProjectId(1);

        log.info(tags.toString());
    }
}