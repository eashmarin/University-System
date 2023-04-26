package ru.nsu.fit.universitysystem.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
