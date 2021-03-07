package com.mutantapi.enumns;

public enum MutantEnum {

    SERVER_UP("The API is up"),
    RESPONSE_TYPE("application/json");

    private final String name;

    MutantEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
