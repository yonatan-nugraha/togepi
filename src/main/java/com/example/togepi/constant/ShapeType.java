package com.example.togepi.constant;

public enum ShapeType {

    CIRCLE("Circle"),
    RECTANGLE("Rectangle"),
    TRIANGLE("Triangle"),
    CUBOID("Cuboid"),
    SPHERE("Sphere");

    private String value;

    ShapeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
