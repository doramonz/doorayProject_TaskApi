package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.tag.TagIdNameListDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import com.nhnacademy.doorayProject.exeption.TagNotFoundException;
import com.nhnacademy.doorayProject.repository.TagRepository;
import com.nhnacademy.doorayProject.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("tagService")
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public TagNameDto getTagName(Integer tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(TagNotFoundException::new);
        return new TagNameDto(tag.getTagName());
    }

    @Override
    public TagIdNameListDto getTagIdNameList(Integer projectId) {
        return new TagIdNameListDto(tagRepository.getTagIdNameList(projectId));
    }

    @Override
    public void addTag(Integer projectId, TagNameDto tagNameDto) {
        Tag tag = new Tag();
        tag.setTagName(tagNameDto.getTagName());
        tag.setProject(new Project(projectId,null,null));
        tagRepository.save(tag);
    }

    @Override
    public void updateTag(Integer tagId, TagNameDto tagNameDto) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(TagNotFoundException::new);
        tag.setTagName(tagNameDto.getTagName());
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(TagNotFoundException::new);
        tagRepository.delete(tag);
    }
}
