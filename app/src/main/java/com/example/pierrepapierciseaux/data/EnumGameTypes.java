package com.example.pierrepapierciseaux.data;

public enum EnumGameTypes {
    CLASSIC("Classic"),VARIANT4("Variant4"),VARIANT7("Variant7");

    private String value;

    EnumGameTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
