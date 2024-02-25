package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.MileStoneIdNameDto;
import com.nhnacademy.doorayProject.dto.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.MileStoneNameDto;
import com.nhnacademy.doorayProject.entity.MileStone;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.exeption.MileStoneNotFoundException;
import com.nhnacademy.doorayProject.repository.MileStoneRepository;
import com.nhnacademy.doorayProject.service.impl.MileStoneServiceImpl;
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
class MileStoneServiceTest {
    @Mock
    private MileStoneRepository mileStoneRepository;

    @InjectMocks
    private MileStoneServiceImpl mileStoneService;

    @Test
    void getMileStoneName_existMileStone() {
        MileStoneNameDto mileStoneNameDto = new MileStoneNameDto("mileStoneName");
        Mockito.when(mileStoneRepository.getMileStoneName(1)).thenReturn(mileStoneNameDto.getMileStoneName());
        assertEquals(mileStoneNameDto.getMileStoneName(), mileStoneService.getMileStoneName(1).getMileStoneName());
    }

    @Test
    void getMileStoneName_notExistMileStone() {
        Mockito.when(mileStoneRepository.getMileStoneName(1)).thenReturn(null);
        assertThrows(MileStoneNotFoundException.class, () -> mileStoneService.getMileStoneName(1));
    }

    @Test
    void getMileStoneIdNameList() {
        MileStoneIdNameListDto mileStoneIdNameListDto = new MileStoneIdNameListDto(List.of(new MileStoneIdNameDto(1, "test1"), new MileStoneIdNameDto(2, "test2")));
        Mockito.when(mileStoneRepository.getMileStoneIdNameList(1)).thenReturn(mileStoneIdNameListDto.getMilestones());
        assertEquals(mileStoneIdNameListDto.getMilestones(), mileStoneService.getMileStoneIdNameList(1).getMilestones());
    }

    @Test
    void addMileStone() {
        Integer projectId = 1;
        MileStoneNameDto mileStoneNameDto = new MileStoneNameDto("test");
        MileStone input = new MileStone(null, mileStoneNameDto.getMileStoneName(), new Project(projectId, null, null));
        MileStone output = new MileStone(1, mileStoneNameDto.getMileStoneName(), new Project(projectId, null, null));
        Mockito.when(mileStoneRepository.save(input)).thenReturn(output);
        assertEquals(mileStoneNameDto.getMileStoneName(), mileStoneService.addMileStone(projectId, mileStoneNameDto).getMileStoneName());
    }

    @Test
    void updateMileStone_existMileStone() {
        Integer milestoneId = 1;
        String after = "after";
        MileStone output = new MileStone(milestoneId, after, new Project(1, null, null));
        Mockito.doReturn(Optional.of(output)).when(mileStoneRepository).findById(milestoneId);
        Mockito.when(mileStoneRepository.save(output)).thenReturn(output);
        assertEquals(after , mileStoneService.updateMileStone(milestoneId, new MileStoneNameDto(after)).getMileStoneName());
    }

    @Test
    void updateMileStone_notExistMileStone() {
        Integer milestoneId = 1;
        Mockito.doReturn(Optional.empty()).when(mileStoneRepository).findById(milestoneId);
        assertThrows(MileStoneNotFoundException.class, () -> mileStoneService.updateMileStone(milestoneId, new MileStoneNameDto("test")));
    }

    @Test
    void deleteMileStone() {
        Integer milestoneId = 1;
        Mockito.doNothing().when(mileStoneRepository).deleteById(milestoneId);
        mileStoneService.deleteMileStone(milestoneId);
        Mockito.verify(mileStoneRepository).deleteById(milestoneId);
    }
}