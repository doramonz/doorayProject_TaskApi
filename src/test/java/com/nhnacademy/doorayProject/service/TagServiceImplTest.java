package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.tag.TagIdNameDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;
import com.nhnacademy.doorayProject.entity.Tag;
import com.nhnacademy.doorayProject.exeption.TagNotFoundException;
import com.nhnacademy.doorayProject.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void getTagName_existTag(){
        Tag tag = new Tag();
        String tagName = "tagName";
        tag.setTagName(tagName);
        Integer tagId = 1;
        tag.setTagId(tagId);
        Mockito.doReturn(Optional.of(tag)).when(tagRepository).findById(tagId);
        Assertions.assertEquals(tagName, tagService.getTagName(tagId).getTagName());
    }

    @Test
    void getTagName_notExistTag(){
        Integer tagId = 1;
        Mockito.doReturn(Optional.empty()).when(tagRepository).findById(tagId);
        Assertions.assertThrows(TagNotFoundException.class, () -> tagService.getTagName(tagId));
    }

    @Test
    void getTagIdNameList() {
        List<TagIdNameDto> tags = List.of();
        Integer projectId = 1;
        Mockito.when(tagRepository.getTagIdNameList(projectId)).thenReturn(tags);
        Assertions.assertEquals(tags, tagService.getTagIdNameList(projectId).getTags());
    }

    @Test
    void addTag() {
        Integer projectId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.when(tagRepository.save(Mockito.any())).thenReturn(new Tag());
        tagService.addTag(projectId, tagNameDto);
        Mockito.verify(tagRepository).save(Mockito.any());
    }

    @Test
    void updateTag_exist(){
        Integer tagId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Tag tag = new Tag();
        Mockito.doReturn(Optional.of(tag)).when(tagRepository).findById(tagId);
        tagService.updateTag(tagId, tagNameDto);
        Mockito.verify(tagRepository).save(tag);
    }

    @Test
    void updateTag_notExist(){
        Integer tagId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.doReturn(Optional.empty()).when(tagRepository).findById(tagId);
        Assertions.assertThrows(TagNotFoundException.class, () -> tagService.updateTag(tagId, tagNameDto));
    }

    @Test
    void deleteTag_exist(){
        Integer tagId = 1;
        Tag tag = new Tag();
        Mockito.doReturn(Optional.of(tag)).when(tagRepository).findById(tagId);
        tagService.deleteTag(tagId);
        Mockito.verify(tagRepository).delete(tag);
    }

    @Test
    void deleteTag_notExist(){
        Integer tagId = 1;
        Mockito.doReturn(Optional.empty()).when(tagRepository).findById(tagId);
        Assertions.assertThrows(TagNotFoundException.class, () -> tagService.deleteTag(tagId));
    }
}