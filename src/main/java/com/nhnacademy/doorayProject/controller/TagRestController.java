package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.tag.TagIdNameListDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;
import com.nhnacademy.doorayProject.service.TagService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class TagRestController {
    private final TagService tagService;

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<TagNameDto> getTag(@PathVariable("tagId") Integer tagId) {
        ResponseEntity<TagNameDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(tagService.getTagName(tagId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{projectId}/tags/list")
    public ResponseEntity<TagIdNameListDto> getTagList(@PathVariable("projectId") Integer projectId) {
        ResponseEntity<TagIdNameListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(tagService.getTagIdNameList(projectId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PostMapping("/{projectId}/tags/upload")
    public ResponseEntity<Void> uploadTagList(@PathVariable("projectId") Integer projectId, @RequestBody TagNameDto tagNameDto) {
        ResponseEntity<Void> responseEntity;
        try {
            tagService.addTag(projectId, tagNameDto);
            responseEntity = ResponseEntity.created(null).build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping("/tags/{tagId}/update")
    public ResponseEntity<Void> updateTag(@PathVariable("tagId") Integer tagId, @RequestBody TagNameDto tagNameDto) {
        ResponseEntity<Void> responseEntity;
        try {
            tagService.updateTag(tagId, tagNameDto);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/tags/{tagId}/delete")
    public ResponseEntity<Void> deleteTag(@PathVariable("tagId") Integer tagId) {
        ResponseEntity<Void> responseEntity;
        try {
            tagService.deleteTag(tagId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }
}
