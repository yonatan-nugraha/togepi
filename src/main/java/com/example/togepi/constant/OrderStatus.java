package com.example.togepi.constant;

public enum OrderStatus {

    SUCCESS("Success"),
    PENDING("Pending"),
    FAILED("Failed");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
