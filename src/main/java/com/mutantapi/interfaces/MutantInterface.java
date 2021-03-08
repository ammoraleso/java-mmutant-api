package com.mutantapi.interfaces;

import com.mutantapi.handlererror.ResponseError;

import java.util.List;

public interface MutantInterface {

    abstract boolean validateMutant(String dna) throws Exception;

    abstract boolean isMutant(List<List<String>> dna) throws Exception;
}
