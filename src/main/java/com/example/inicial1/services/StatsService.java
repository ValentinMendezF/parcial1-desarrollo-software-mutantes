package com.example.inicial1.services;

import com.example.inicial1.entities.Stats;
import com.example.inicial1.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRepository dnaRepository;

    @Autowired
    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public Stats getStats() {
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        double ratio = countHumanDna > 0 ? (double) countMutantDna / countHumanDna : 0;

        return new Stats(countMutantDna, countHumanDna, ratio);
    }

}
