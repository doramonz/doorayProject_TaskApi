package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.tag.TagIdNameDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;
import com.nhnacademy.doorayProject.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    @Query("SELECT new com.nhnacademy.doorayProject.dto.tag.TagIdNameDto(t.tagId, t.tagName) FROM Tag t WHERE t.project.projectId = :projectId")
    List<TagIdNameDto> getTagIdNameList(Integer projectId);
}
