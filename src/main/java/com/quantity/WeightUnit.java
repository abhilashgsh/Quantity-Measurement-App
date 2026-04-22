package com.quantity;

public enum WeightUnit {
    KG(1.0),
    GRAM(0.001),
    POUND(0.45359237);

    private final double baseUnitFactor;

    WeightUnit(double baseUnitFactor) {
        this.baseUnitFactor = baseUnitFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * baseUnitFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / baseUnitFactor;
    }
}
