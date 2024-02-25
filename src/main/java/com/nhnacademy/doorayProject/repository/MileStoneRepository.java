package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.milestone.MileStoneIdNameDto;
import com.nhnacademy.doorayProject.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MileStoneRepository extends JpaRepository<MileStone, Integer>{
    @Query("SELECT m.mileStoneName FROM MileStone m WHERE m.mileStoneId = :mileStoneId")
    String getMileStoneName(Integer mileStoneId);

    @Query("SELECT new com.nhnacademy.doorayProject.dto.milestone.MileStoneIdNameDto(m.mileStoneId, m.mileStoneName) FROM MileStone m JOIN Project p on m.project.projectId = p.projectId WHERE p.projectId = :projectId")
    List<MileStoneIdNameDto> getMileStoneIdNameList(Integer projectId);
}
