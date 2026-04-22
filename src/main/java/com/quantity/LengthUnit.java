package com.quantity;

public enum LengthUnit {
    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0);

    private final double baseUnitFactor;

    LengthUnit(double baseUnitFactor) {
        this.baseUnitFactor = baseUnitFactor;
    }

    double toBaseUnit(double value) {
        return value * baseUnitFactor;
    }
}
