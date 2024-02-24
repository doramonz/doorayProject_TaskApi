package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.repository.MileStoneRepository;
import com.nhnacademy.doorayProject.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("mileStoneService")
public class MileStoneServiceImpl implements MileStoneService {
    private final MileStoneRepository mileStoneRepository;
    @Override
    public String getMileStoneName(Integer mileStoneId) {
        return mileStoneRepository.getMileStoneName(mileStoneId);
    }
}
