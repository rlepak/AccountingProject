package com.project.enums;

public enum Unit {
    KG("KG"), LB("LB"), PIECE("Piece"), ITEM("Item"), METER("Meter"), GALLON("Gallon");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}










