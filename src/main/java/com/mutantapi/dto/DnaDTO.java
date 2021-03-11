package com.mutantapi.dto;

public class DnaDTO {

    private String dna;
    private boolean isMutant;

    public DnaDTO(final String dna,final boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
