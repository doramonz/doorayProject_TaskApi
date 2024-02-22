package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Tag;
import com.nhnacademy.doorayProject.exeption.NotFoundTags;
import com.nhnacademy.doorayProject.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTag(int tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (tag.isEmpty()) {
            throw new NotFoundTags();
        }
        return tag.get();
    }

    public List<Tag> getAllTag() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            throw new NotFoundTags();
        }
        return tags;
    }

    public TagNameDto addTag(Tag tag) {
        if (Objects.isNull(tag)) {
            throw new IllegalStateException();
        }
        TagNameDto tagNameDto = (TagNameDto) tagRepository.save(tag);

        return tagNameDto;
    }

    public TagNameDto modifyTag(int tagId,String tagName) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (tag.isEmpty()) {
            throw new NotFoundTags();
        }
        Tag newTag = new Tag();
        newTag.setTagId(tagId);
        newTag.setTagName(tagName);
        newTag.setProjectId(tag.get().getProjectId());
        TagNameDto tagNameDto = (TagNameDto) tagRepository.save(newTag);

        return tagNameDto;
    }

    public void deleteTag(int tagId) {
        tagRepository.deleteById(tagId);

    }
}
