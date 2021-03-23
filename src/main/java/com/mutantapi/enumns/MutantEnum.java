package com.mutantapi.enumns;

public enum MutantEnum {

    SERVER_UP("The API is up"), RESPONSE_TYPE("application/json"),

    DESC_MUTANT("El individuo es MUTANTE"), DESC_HUMAN("El individuo es HUMAN"),

    ID_STATS("id_stats");

    private final String name;

    MutantEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
