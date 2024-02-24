package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/projects")
@RequiredArgsConstructor
@RestController
public class MileStoneRestController {
    private final MileStoneService mileStoneService;

    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<Map<String,String>> getMileStoneInfo(@PathVariable("milestoneId") String milestoneId) {
        ResponseEntity<Map<String,String>> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(Map.of("milestoneName", mileStoneService.getMileStoneName(Integer.parseInt(milestoneId))));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }
}
