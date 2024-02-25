package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.tag.TagIdNameListDto;
import com.nhnacademy.doorayProject.dto.tag.TagNameDto;
import com.nhnacademy.doorayProject.exeption.TagNotFoundException;
import com.nhnacademy.doorayProject.service.TagService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TagRestController.class)
@AutoConfigureMockMvc
class TagRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Test
    void getTag() throws Exception {
        Integer tagId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.when(tagService.getTagName(tagId)).thenReturn(tagNameDto);
        mockMvc.perform(get("/projects/tags/{tagId}", tagId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(tagNameDto)));
    }

    @Test
    void getTagList() throws Exception {
        Integer projectId = 1;
        TagIdNameListDto tagIdNameListDto = new TagIdNameListDto(List.of());
        Mockito.when(tagService.getTagIdNameList(projectId)).thenReturn(tagIdNameListDto);
        mockMvc.perform(get("/projects/{projectId}/tags/list", projectId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(tagIdNameListDto)));
    }

    @Test
    void uploadTagList() throws Exception {
        Integer projectId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.doNothing().when(tagService).addTag(projectId, tagNameDto);
        mockMvc.perform(post("/projects/{projectId}/tags/upload", projectId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(tagNameDto)))
                .andExpect(status().isCreated());
    }
    @Test
    void updateTag_exist() throws Exception {
        Integer tagId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.doNothing().when(tagService).updateTag(any(), any());
        mockMvc.perform(put("/projects/tags/{tagId}/update", tagId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(tagNameDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateTag_notExist() throws Exception {
        Integer tagId = 1;
        TagNameDto tagNameDto = new TagNameDto("tagName");
        Mockito.doThrow(TagNotFoundException.class).when(tagService).updateTag(any(), any());
        mockMvc.perform(put("/projects/tags/{tagId}/update", tagId)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(tagNameDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTag_exist() throws Exception {
        Integer tagId = 1;
        Mockito.doNothing().when(tagService).deleteTag(tagId);
        mockMvc.perform(delete("/projects/tags/{tagId}/delete", tagId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTag_notExist() throws Exception {
        Integer tagId = 1;
        Mockito.doThrow(TagNotFoundException.class).when(tagService).deleteTag(tagId);
        mockMvc.perform(delete("/projects/tags/{tagId}/delete", tagId))
                .andExpect(status().isBadRequest());
    }
}