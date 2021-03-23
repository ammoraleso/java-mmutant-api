package com.mutantapi.interfaces;

import com.mutantapi.response.ResponseMutant;

import java.util.List;

public interface MutantInterface {

    abstract ResponseMutant validateMutant(String dna) throws Exception;

    abstract boolean isMutant(final List<List<String>> dna) throws Exception;
}
