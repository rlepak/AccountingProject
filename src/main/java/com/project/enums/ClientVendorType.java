package com.project.enums;

public enum ClientVendorType {

    VENDOR("Vendor"), CLIENT("Client"), VENDOR_CLIENT("Vendor/Client");

    private final String value;

    ClientVendorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
        }
}
