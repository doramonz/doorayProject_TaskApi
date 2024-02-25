package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.ProjectDto;
import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import com.nhnacademy.doorayProject.exeption.NotFoundTags;
import com.nhnacademy.doorayProject.repository.ProjectRepository;
import com.nhnacademy.doorayProject.repository.TagRepository;
import com.nhnacademy.doorayProject.repository.TaskTagMappingRepository;
import com.nhnacademy.doorayProject.service.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    private final TaskTagMappingRepository taskTagMappingRepository;

    private final ProjectRepository projectRepository;

    public TagServiceImpl(TagRepository tagRepository, TaskTagMappingRepository taskTagMappingRepository, ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.taskTagMappingRepository = taskTagMappingRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public TagNameDto getTag(Integer projectId, Integer tagId) {

        Project project = getProjectId(projectId);
        List<Tag> tags = tagRepository.findByProjectId(project);
        for (Tag tag : tags) {
            if (tag.getTagId().equals(tagId)) {
                TagNameDto tagNameDto = new TagNameDto();
                tagNameDto.setTagName(tag.getTagName());

                return tagNameDto;
            }
        }
        return null;
    }

    @Override
    public List<TagNameDto> getTags(Integer projectId) {


        Project project = getProjectId(projectId);
        List<Tag> tags = tagRepository.findByProjectId(project);
        List<TagNameDto> tagNameDtos = new ArrayList<>();
        for (Tag tag : tags) {
            tagNameDtos.add(new TagNameDto(tag.getTagName()));
        }

        return tagNameDtos;
    }

    @Override
    public void addTag(Integer projectId,TagNameDto tag) {

        Project project = getProjectId(projectId);
        Tag newTag = new Tag(getTagId()+1, tag.getTagName(), project);

        tagRepository.save(newTag);

    }

    @Override
    public TagNameDto updateTag(Integer projectId, Integer tagId,TagNameDto tag) {
        Project project = getProjectId(projectId);
        List<Tag> tags = tagRepository.findByProjectId(project);
        TagNameDto tagNameDto = new TagNameDto();

        for (Tag newTag : tags) {
            if (newTag.getTagId().equals(tagId)) {
                newTag.setTagName(tag.getTagName());
                tagRepository.save(newTag);
                tagNameDto.setTagName(newTag.getTagName());

                break;
            }
        }
        return tagNameDto;
    }

    @Override
    public void deleteTag(Integer projectId, Integer tagId) {
        Project project = getProjectId(projectId);
        List<Tag> tags = tagRepository.findByProjectId(project);
        for (Tag newTag : tags) {
            if (newTag.getTagId().equals(tagId)) {
                tagRepository.deleteById(tagId);
                break;
            }
        }
    }

    @Override
    public Project getProjectId(Integer projectId) {

        ProjectDto projectDto = projectRepository.findByProjectId(projectId);
        Project project = new Project(projectId, projectDto.getName(), projectDto.getStatus());
        return project;
    }

    @Override
    public int getTagId() {

        return tagRepository.findAll().size();
    }

}
