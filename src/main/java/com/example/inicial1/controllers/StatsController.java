package com.example.inicial1.controllers;

import com.example.inicial1.entities.Stats;
import com.example.inicial1.services.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<Stats> getStats() {
        Stats stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
