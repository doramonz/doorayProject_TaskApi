package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.MileStoneNameDto;

import java.util.List;

public interface MileStoneService {
    MileStoneNameDto getMileStoneName(Integer mileStoneId);
    MileStoneIdNameListDto getMileStoneIdNameList(Integer projectId);

    MileStoneNameDto addMileStone(Integer projectId, MileStoneNameDto mileStoneNameDto);

    MileStoneNameDto updateMileStone(Integer milestoneId, MileStoneNameDto mileStoneNameDto);

    void deleteMileStone(Integer milestoneId);
}
