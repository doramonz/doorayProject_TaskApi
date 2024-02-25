package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Tag;
import com.nhnacademy.doorayProject.exeption.NotFoundTags;
import com.nhnacademy.doorayProject.repository.TagRepository;
import com.nhnacademy.doorayProject.repository.TaskTagMappingRepository;
import com.nhnacademy.doorayProject.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    private final TaskTagMappingRepository taskTagMappingRepository;

    public TagServiceImpl(TagRepository tagRepository, TaskTagMappingRepository taskTagMappingRepository) {
        this.tagRepository = tagRepository;
        this.taskTagMappingRepository = taskTagMappingRepository;
    }

    @Override
    public TagNameDto getTag(Integer projectId, Integer tagId) {
//        List<Tag> tags = tagRepository.findByProjectId(projectId);


        return null;
    }

    @Override
    public List<TagNameDto> getTags(Integer projectId) {
        return null;
    }

    @Override
    public void addTag(Integer projectId,Tag tag) {

    }

    @Override
    public TagNameDto updateTag(Integer proejctId, Integer tagId,Tag tag) {
        return null;
    }

    @Override
    public void deleteTag(Integer proejctId, Integer tagId) {

    }
}
