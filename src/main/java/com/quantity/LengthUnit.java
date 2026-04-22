package com.quantity;

public enum LengthUnit {
    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(1.0 / 2.54);

    private final double baseUnitFactor;

    LengthUnit(double baseUnitFactor) {
        this.baseUnitFactor = baseUnitFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * baseUnitFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / baseUnitFactor;
    }

    double toBaseUnit(double value) {
        return value * baseUnitFactor;
    }

    double fromBaseUnit(double baseValue) {
        return baseValue / baseUnitFactor;
    }
}
