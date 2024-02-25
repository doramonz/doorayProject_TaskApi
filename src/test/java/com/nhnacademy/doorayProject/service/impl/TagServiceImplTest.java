package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class TagServiceImplTest {
    @Autowired
    private TagServiceImpl service;


    @Test
    void getProjectId() {
        Project project = new Project(1, "modify", "중지");

        Project id = service.getProjectId(1);
        assertThat(project).isEqualTo(id);
    }

    @Test
    void getTag() {
        TagNameDto tagNameDto = service.getTag(1, 1);
        TagNameDto actual = new TagNameDto("tag1");
        assertThat(actual).isEqualTo(tagNameDto);

        TagNameDto tagNameDto2 = service.getTag(1, 3);
        TagNameDto actual2 = new TagNameDto("tag3");
        assertThat(actual2).isEqualTo(tagNameDto2);

    }

    @Test
    void getTags() {

        List<TagNameDto> getTags = service.getTags(1);
        assertThat(getTags.size()).isEqualTo(6);
    }

    @Test
    void addTag() {

        service.addTag(2, new TagNameDto("AddTagName"));
        assertThat(service.getTags(2).size()).isEqualTo(1);

    }

    @Test
    void updateTag() {

        service.updateTag(1, 1, new TagNameDto("updateTagTest"));
        assertThat(service.getTag(1, 1).getTagName()).isEqualTo("updateTagTest");

    }

    @Test
    void deleteTag() {
        service.deleteTag(1, 3);
        assertThat(service.getTags(1).size()).isEqualTo(4);
    }
}