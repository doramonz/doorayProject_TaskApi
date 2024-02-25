package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.MileStoneNameDto;
import com.nhnacademy.doorayProject.entity.MileStone;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.exeption.MileStoneNotFoundException;
import com.nhnacademy.doorayProject.repository.MileStoneRepository;
import com.nhnacademy.doorayProject.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("mileStoneService")
public class MileStoneServiceImpl implements MileStoneService {
    private final MileStoneRepository mileStoneRepository;

    @Override
    public MileStoneNameDto getMileStoneName(Integer mileStoneId) {
        String mileStoneName = mileStoneRepository.getMileStoneName(mileStoneId);
        if (mileStoneName == null) {
            throw new MileStoneNotFoundException();
        }
        return new MileStoneNameDto(mileStoneName);
    }

    @Override
    public MileStoneIdNameListDto getMileStoneIdNameList(Integer projectId) {
        return new MileStoneIdNameListDto(mileStoneRepository.getMileStoneIdNameList(projectId));
    }

    @Override
    public MileStoneNameDto addMileStone(Integer projectId, MileStoneNameDto mileStoneNameDto) {
        return new MileStoneNameDto(mileStoneRepository.save(new MileStone(null, mileStoneNameDto.getMileStoneName(), new Project(projectId, null, null))).getMileStoneName());
    }

    @Override
    public MileStoneNameDto updateMileStone(Integer milestoneId, MileStoneNameDto mileStoneNameDto) {
        MileStone mileStone = mileStoneRepository.findById(milestoneId).orElseThrow(MileStoneNotFoundException::new);
        mileStone.setMileStoneName(mileStoneNameDto.getMileStoneName());
        return new MileStoneNameDto(mileStoneRepository.save(mileStone).getMileStoneName());
    }

    @Override
    public void deleteMileStone(Integer milestoneId) {
        mileStoneRepository.deleteById(milestoneId);
    }
}
