package com.mutantapi.dto;

public class StatsDTO {

    private int id_stats;
    private double count_mutants_dna;
    private double count_humans_dna;
    private double ratio;

    public StatsDTO() {
    }

    public StatsDTO(final double count_mutants_dna, final double count_humans_dna) {
        this.id_stats = 1;
        this.count_mutants_dna = count_mutants_dna;
        this.count_humans_dna = count_humans_dna;
        if (count_humans_dna == 0) {
            this.ratio = 1;
        } else {
            this.ratio = count_mutants_dna/count_humans_dna;
        }
    }

    public int getId_stats() {
        return id_stats;
    }

    public void setId_stats(int id_stats) {
        this.id_stats = id_stats;
    }

    public double getCount_mutants_dna() {
        return count_mutants_dna;
    }

    public void setCount_mutants_dna(double count_mutants_dna) {
        this.count_mutants_dna = count_mutants_dna;
    }

    public double getCount_humans_dna() {
        return count_humans_dna;
    }

    public void setCount_humans_dna(double count_humans_dna) {
        this.count_humans_dna = count_humans_dna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
