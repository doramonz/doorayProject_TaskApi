package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Tag;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;

import java.util.List;

public interface TagService {


    TagNameDto getTag(Integer projectId,Integer tagId);

    List<TagNameDto> getTags(Integer projectId);

    void addTag(Integer projectId, TagNameDto tag);

    TagNameDto updateTag(Integer proejctId, Integer tagId,TagNameDto tag);

    void deleteTag(Integer proejctId, Integer tagId);

    Project getProjectId(Integer projectId);

    int getTagId();

}
