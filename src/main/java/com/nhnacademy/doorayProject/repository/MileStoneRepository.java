package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MileStoneRepository extends JpaRepository<MileStone, Integer>{
    @Query("SELECT m.mileStoneName FROM MileStone m WHERE m.mileStoneId = :mileStoneId")
    String getMileStoneName(Integer mileStoneId);
}
