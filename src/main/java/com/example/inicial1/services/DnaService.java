package com.example.inicial1.services;

import com.example.inicial1.entities.Dna;
import com.example.inicial1.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DnaService {
    private final DnaRepository dnaRepository;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Verificar secuencias horizontales
        for (String row : dna) {
            count += countSequences(row);
        }

        // Verificar secuencias verticales
        for (int col = 0; col < n; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < n; row++) {
                column.append(dna[row].charAt(col));
            }
            count += countSequences(column.toString());
        }

        // Verificar secuencias oblicuas
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                count += countDiagonalSequences(dna, row, col, 1, 1);
                count += countDiagonalSequences(dna, row, col, 1, -1);
            }
        }

        return count > 1;
    }

    private int countSequences(String sequence) {
        int count = 0;
        for (int i = 0; i <= sequence.length() - 4; i++) {
            if (sequence.charAt(i) == sequence.charAt(i + 1) &&
                    sequence.charAt(i) == sequence.charAt(i + 2) &&
                    sequence.charAt(i) == sequence.charAt(i + 3)) {
                count++;
            }
        }
        return count;
    }

    private int countDiagonalSequences(String[] dna, int startRow, int startCol, int rowIncrement, int colIncrement) {
        int count = 0;
        int n = dna.length;
        int row = startRow;
        int col = startCol;

        while (row < n && col >= 0 && col < n) {
            char current = dna[row].charAt(col);
            int length = 1;
            int nextRow = row + rowIncrement;
            int nextCol = col + colIncrement;

            while (nextRow < n && nextCol >= 0 && nextCol < n && dna[nextRow].charAt(nextCol) == current) {
                length++;
                nextRow += rowIncrement;
                nextCol += colIncrement;
            }

            if (length >= 4) {
                count++;
            }

            row += rowIncrement;
            col += colIncrement;
        }

        return count;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificamos si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            // Si el ADN ya fue analizado, retornamos el resultado
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant(dna);
    }
}

