package com.mutantapi.services;

import com.mutantapi.database.DnaDao;
import com.mutantapi.dto.DnaDTO;
import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.interfaces.MutantInterface;
import com.mutantapi.response.ResponseMutant;
import com.mutantapi.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MutantServiceImpl implements MutantInterface {

    private int counterSequence = 0;
    private String[] lettersDna = { "A", "T", "G", "C" };

    private DnaDao dnaDao;
    private StateServiceImpl stateService;

    public MutantServiceImpl() {
        this.dnaDao = new DnaDao();
        this.stateService = new StateServiceImpl();
    }

    @Override
    public ResponseMutant validateMutant(final String dna) throws Exception {
        final List<List<String>> matrix = convertToMatrix(dna);
        boolean isMutant = this.isMutant(matrix);
        final ResponseMutant res = createResp(isMutant);
        final DnaDTO dnaDTO = new DnaDTO(JsonUtil.formatDnaString(dna), isMutant ? 1 : 0);
        stateService.saveStats(JsonUtil.formatDnaString(dna), isMutant);
        dnaDao.add(dnaDTO);
        return res;
    }

    private ResponseMutant createResp(boolean isMutant) {
        ResponseMutant res = new ResponseMutant();
        res.setMutant(isMutant);
        res.setDescription(MutantEnum.DESC_MUTANT.getName());
        if (!isMutant) {
            res.setDescription(MutantEnum.DESC_HUMAN.getName());
        }
        return res;
    }

    @Override
    public boolean isMutant(final List<List<String>> matrix) {
        counterSequence = 0;
        for (int i = 0; i < matrix.size(); i++) {
            getLinesWithSequence(matrix, i);
        }
        ;
        return counterSequence > 1;
    }

    private void getLinesWithSequence(final List<List<String>> matrix, final int index) {
        getDiagCount(matrix, index);
        getRowColCount(matrix, index);
    }

    private void getDiagCount(final List<List<String>> matrix, final int index) {
        int restDiag = 0;
        int counterSupDerSeq = 0;
        int counterSupIzqSeq = 0;
        int counterInfDerSeq = 0;
        int counterInfIzqSeq = 0;

        for (int i = 0; i <= matrix.size() - index - 2; i++) {
            restDiag = matrix.size() - i - index;
            if (i == 0 && restDiag < 4) {
                return;
            }
            if (counterSequence > 1) {
                return;
            }
            if (restDiag < 4 && counterSupDerSeq == 0 && counterSupIzqSeq == 0 && counterInfDerSeq == 0
                    && counterInfIzqSeq == 0) {
                break;
            }
            counterSupDerSeq = matrix.get(i).get(i + index).equals(matrix.get(i + 1).get(i + index + 1))
                    ? counterSupDerSeq + 1
                    : 0;

            counterSupIzqSeq = matrix.get(i).get(matrix.size() - index - i - 1)
                    .equals(matrix.get(i + 1).get(matrix.size() - index - i - 2)) ? counterSupIzqSeq + 1 : 0;

            if (index > 0) {
                counterInfDerSeq = matrix.get(i + index).get(i).equals(matrix.get(i + 1 + index).get(i + 1))
                        ? counterInfDerSeq + 1
                        : 0;

                counterInfIzqSeq = matrix.get(i + index).get(matrix.size() - i - 1)
                        .equals(matrix.get(i + index + 1).get(matrix.size() - i - 2)) ? counterInfIzqSeq + 1 : 0;
            }

            if (counterSupDerSeq == 3) {
                counterSupDerSeq = 0;
                counterSequence++;
            }
            if (counterSupIzqSeq == 3) {
                counterSupIzqSeq = 0;
                counterSequence++;
            }

            if (counterInfDerSeq == 3) {
                counterInfDerSeq = 0;
                counterSequence++;
            }
            if (counterInfIzqSeq == 3) {
                counterInfIzqSeq = 0;
                counterSequence++;
            }
        }
    }

    private void getRowColCount(final List<List<String>> matrix, final int index) {
        int counterColumn = 0;
        int counterRow = 0;
        int rest = 0;
        if (counterSequence > 1) {
            return;
        }

        // Because always i'm comparing with the next register.
        for (int i = 0; i <= matrix.size() - 2; i++) {
            rest = matrix.size() - i;
            if (counterSequence > 1) {
                return;
            }
            if (rest < 4 && counterColumn == 0 && counterRow == 0) {
                break;
            }

            counterColumn = matrix.get(i).get(index).equals(matrix.get(i + 1).get(index)) ? counterColumn + 1 : 0;
            counterRow = matrix.get(index).get(i).equals(matrix.get(index).get(i + 1)) ? counterRow + 1 : 0;

            // Because if counterColum or CounterRow are 3 is becase it have the same
            // letters in 4 positions
            if (counterColumn == 3) {
                counterColumn = 0;
                counterSequence++;
            }
            if (counterRow == 3) {
                counterRow = 0;
                counterSequence++;
            }
        }
    }

    private List<List<String>> convertToMatrix(final String dna) throws Exception {
        final String dnaToMatrix = JsonUtil.formatDnaString(dna);

        final String[] vector = dnaToMatrix.split(",");
        final List<List<String>> matrix = new ArrayList<>();

        Arrays.stream(vector).forEach(line -> {
            // Validate if is NXN Matrix
            if (vector.length != line.trim().length()) {
                throw new IllegalArgumentException("Length of Matrix and elements are not equal");
            }

            List<String> tmpArray = new ArrayList<>();
            char[] lineList = line.trim().toCharArray();
            for (char letter : lineList) {
                if (Arrays.asList(lettersDna).contains(String.valueOf(letter))) {
                    tmpArray.add(String.valueOf(letter));
                } else {
                    throw new IllegalArgumentException("Letter is not allowed for dna");
                }
            }
            matrix.add(tmpArray);
        });
        return matrix;
    }
}
