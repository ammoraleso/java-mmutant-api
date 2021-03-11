package com.mutantapi.dto;

public class DnaDTO {

    private String dna;
    private int isMutant;

    public DnaDTO() {
    }

    public DnaDTO(final String dna, final int isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public int getIsMutant() {
        return isMutant;
    }

    public void setIsMutant(int isMutant) {
        this.isMutant = isMutant;
    }
}
