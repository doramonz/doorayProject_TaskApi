package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.milestone.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.milestone.MileStoneNameDto;

public interface MileStoneService {
    MileStoneNameDto getMileStoneName(Integer mileStoneId);
    MileStoneIdNameListDto getMileStoneIdNameList(Integer projectId);

    MileStoneNameDto addMileStone(Integer projectId, MileStoneNameDto mileStoneNameDto);

    MileStoneNameDto updateMileStone(Integer milestoneId, MileStoneNameDto mileStoneNameDto);

    void deleteMileStone(Integer milestoneId);
}
