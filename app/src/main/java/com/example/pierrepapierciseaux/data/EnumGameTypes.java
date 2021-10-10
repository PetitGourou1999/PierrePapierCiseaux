package com.example.pierrepapierciseaux.data;

/**
 * Enumération des types de jeu
 */
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
