package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.MileStoneIdNameDto;
import com.nhnacademy.doorayProject.dto.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.MileStoneNameDto;
import com.nhnacademy.doorayProject.exeption.MileStoneNotFoundException;
import com.nhnacademy.doorayProject.service.MileStoneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MileStoneRestController.class)
@AutoConfigureMockMvc
class MileStoneRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MileStoneService mileStoneService;

    @Test
    void getMileStoneInfo_existMileStone() throws Exception {
        Integer milestoneId = 1;
        MileStoneNameDto mileStoneName = new MileStoneNameDto("mileStoneName");
        Mockito.when(mileStoneService.getMileStoneName(milestoneId)).thenReturn(mileStoneName);
        mockMvc.perform(get("/projects/milestones/{milestoneId}", milestoneId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mileStoneName").value(mileStoneName.getMileStoneName()));
    }

    @Test
    void getMileStoneInfo_notExistMileStone() throws Exception {
        Integer milestoneId = 1;
        Mockito.when(mileStoneService.getMileStoneName(milestoneId)).thenThrow(MileStoneNotFoundException.class);
        mockMvc.perform(get("/projects/milestones/{milestoneId}", milestoneId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMileStoneList() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Integer projectId = 1;
        MileStoneIdNameListDto mileStoneIdNameListDto = new MileStoneIdNameListDto();
        mileStoneIdNameListDto.setMilestones(List.of());
        Mockito.when(mileStoneService.getMileStoneIdNameList(projectId)).thenReturn(mileStoneIdNameListDto);
        mockMvc.perform(get("/projects/{projectId}/milestones/list", projectId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mileStoneIdNameListDto)));

        mileStoneIdNameListDto.setMilestones(List.of(new MileStoneIdNameDto(1, "test1"), new MileStoneIdNameDto(2, "test2")));
        Mockito.when(mileStoneService.getMileStoneIdNameList(projectId)).thenReturn(mileStoneIdNameListDto);
        mockMvc.perform(get("/projects/{projectId}/milestones/list", projectId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mileStoneIdNameListDto)));
    }

    @Test
    void addMileStone() throws Exception {
        Integer projectId = 1;
        MileStoneNameDto mileStoneNameDto = new MileStoneNameDto("test");
        Mockito.when(mileStoneService.addMileStone(projectId, mileStoneNameDto)).thenReturn(mileStoneNameDto);
        mockMvc.perform(post("/projects/{projectId}/milestones/upload", projectId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mileStoneNameDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateMileStone() throws Exception{
        Integer milestoneId = 1;
        MileStoneNameDto mileStoneNameDto = new MileStoneNameDto("test");
        Mockito.when(mileStoneService.updateMileStone(milestoneId, mileStoneNameDto)).thenReturn(mileStoneNameDto);
        mockMvc.perform(put("/projects/milestones/{milestoneId}/update", milestoneId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mileStoneNameDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMileStone_existMileStone() throws Exception {
        Integer milestoneId = 1;
        Mockito.doNothing().when(mileStoneService).deleteMileStone(milestoneId);
        mockMvc.perform(delete("/projects/milestones/{milestoneId}/delete", milestoneId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMileStone_notExistMileStone() throws Exception {
        Integer milestoneId = 1;
        Mockito.doThrow(MileStoneNotFoundException.class).when(mileStoneService).deleteMileStone(milestoneId);
        mockMvc.perform(delete("/projects/milestones/{milestoneId}/delete", milestoneId))
                .andExpect(status().isBadRequest());
    }
}