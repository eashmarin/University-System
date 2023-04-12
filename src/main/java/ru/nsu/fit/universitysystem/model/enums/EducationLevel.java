package ru.nsu.fit.universitysystem.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EducationLevel {
    BACHELOR("BACHELOR"),
    MASTER("MASTER"),
    POSTGRADUATE("POSTGRADUATE");

    private final String value;

    EducationLevel(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
