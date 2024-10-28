package com.example.inicial1.controllers;


import com.example.inicial1.services.DnaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class DnaController {
    private final DnaService dnaService;
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<Void> checkMutant(@RequestBody Map<String, String[]> request) {
        String[] dna = request.get("dna");
        boolean isMutant = dnaService.analyzeDna(dna);

        if (isMutant) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
