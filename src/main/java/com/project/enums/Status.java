package com.project.enums;

public enum Status {
    ACTIVE("Active"), CLOSED("Closed");

    private final String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
