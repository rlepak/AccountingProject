package com.project.enums;

public enum InvoiceType {
    SALE("Sale"), PURCHASE("Purchase");

    private final String value;

    private InvoiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
