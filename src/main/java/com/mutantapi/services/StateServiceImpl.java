package com.mutantapi.services;

import com.mutantapi.database.DnaDao;
import com.mutantapi.database.StatsDao;
import com.mutantapi.dto.DnaDTO;
import com.mutantapi.dto.StatsDTO;

public class StateServiceImpl {

    private DnaDao dnaDao;
    private StatsDao statsDao;

    public StateServiceImpl() {
        this.dnaDao = new DnaDao();
        this.statsDao = new StatsDao();
    }

    public void saveStats(final String dna, final boolean isMutant) {
        final DnaDTO dnaFound = dnaDao.findDna(dna);
        if (dnaFound != null) {
            return;
        }
        StatsDTO stats = statsDao.findAll();

        if (stats == null) {
            if (isMutant) {
                stats = new StatsDTO(1, 0);
            } else {
                stats = new StatsDTO(0, 1);
            }
        } else {
            if (isMutant) {
                stats = new StatsDTO(
                        stats.getCount_mutants_dna() + 1,
                        stats.getCount_humans_dna()
                );
            } else {
                stats = new StatsDTO(
                        stats.getCount_mutants_dna(),
                        stats.getCount_humans_dna() + 1
                );
            }
        }
        statsDao.add(stats);
    }

    public StatsDTO getStats() {
        return statsDao.findAll();
    }
}
