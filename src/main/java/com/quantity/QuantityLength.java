package com.quantity;

import java.util.Objects;

public final class QuantityLength {
    public static final double EPSILON = 0.000000001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        this.value = value;
        this.unit = Objects.requireNonNull(unit, "unit must not be null");
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public static double convert(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Objects.requireNonNull(fromUnit, "fromUnit must not be null");
        Objects.requireNonNull(toUnit, "toUnit must not be null");
        return toUnit.fromBaseUnit(fromUnit.toBaseUnit(value));
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        return new QuantityLength(convert(value, unit, targetUnit), targetUnit);
    }

    private double baseValue() {
        return unit.toBaseUnit(value);
    }

    private double normalizedValue() {
        return baseValue();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QuantityLength)) {
            return false;
        }
        QuantityLength that = (QuantityLength) other;
        return Math.abs(normalizedValue() - that.normalizedValue()) <= EPSILON;
    }

    @Override
    public int hashCode() {
        return QuantityLength.class.hashCode();
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
