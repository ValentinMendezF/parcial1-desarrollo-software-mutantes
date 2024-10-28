package com.example.inicial1.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dna extends Base implements Serializable {

    private String dna;

    private boolean isMutant;
}
