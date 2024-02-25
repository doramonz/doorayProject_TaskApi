package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.milestone.MileStoneIdNameListDto;
import com.nhnacademy.doorayProject.dto.milestone.MileStoneNameDto;
import com.nhnacademy.doorayProject.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/projects")
@RequiredArgsConstructor
@RestController
public class MileStoneRestController {
    private final MileStoneService mileStoneService;

    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<MileStoneNameDto> getMileStoneInfo(@PathVariable("milestoneId") Integer milestoneId) {
        ResponseEntity<MileStoneNameDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(mileStoneService.getMileStoneName(milestoneId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @GetMapping("/{projectId}/milestones/list")
    public ResponseEntity<MileStoneIdNameListDto> getMileStoneList(@PathVariable("projectId") Integer projectId) {
        ResponseEntity<MileStoneIdNameListDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(mileStoneService.getMileStoneIdNameList(projectId));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PostMapping("/{projectId}/milestones/upload")
    public ResponseEntity<MileStoneNameDto> addMileStone(@PathVariable("projectId") Integer projectId, @RequestBody MileStoneNameDto mileStoneNameDto) {
        ResponseEntity<MileStoneNameDto> responseEntity;
        try {
            mileStoneService.addMileStone(projectId, mileStoneNameDto);
            responseEntity = ResponseEntity.created(null).build();
            log.error("responseEntity: {}", responseEntity);
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping("/milestones/{milestoneId}/update")
    public ResponseEntity<MileStoneNameDto> updateMileStone(@PathVariable("milestoneId") Integer milestoneId, @RequestBody MileStoneNameDto mileStoneNameDto) {
        ResponseEntity<MileStoneNameDto> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(mileStoneService.updateMileStone(milestoneId, mileStoneNameDto));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/milestones/{milestoneId}/delete")
    public ResponseEntity<Void> deleteMileStone(@PathVariable("milestoneId") Integer milestoneId) {
        ResponseEntity<Void> responseEntity;
        try {
            mileStoneService.deleteMileStone(milestoneId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

}
