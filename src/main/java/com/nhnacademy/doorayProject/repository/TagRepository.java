package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
//    List<TagNameDto> findAllByTagName();

    List<Tag> findByProjectId(Project project);
}
