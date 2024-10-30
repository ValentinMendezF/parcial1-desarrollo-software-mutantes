package com.example.inicial1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Stats {
    private final long countMutantDna;
    private final long countHumanDna;
    private final double ratio;

}

