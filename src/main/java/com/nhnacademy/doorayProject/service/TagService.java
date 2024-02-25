package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.tag.TagIdNameListDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;

public interface TagService {
    TagNameDto getTagName(Integer tagId);

    TagIdNameListDto getTagIdNameList(Integer projectId);

    void addTag(Integer projectId, TagNameDto tagNameDto);

    void updateTag(Integer tagId, TagNameDto tagNameDto);

    void deleteTag(Integer tagId);
}
