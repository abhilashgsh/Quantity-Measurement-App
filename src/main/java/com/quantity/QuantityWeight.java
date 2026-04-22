package com.quantity;

import java.util.Objects;

public final class QuantityWeight {
    public static final double EPSILON = 0.000000001;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        this.value = value;
        this.unit = Objects.requireNonNull(unit, "unit must not be null");
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public static double convert(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        Objects.requireNonNull(fromUnit, "fromUnit must not be null");
        Objects.requireNonNull(toUnit, "toUnit must not be null");
        return toUnit.convertFromBaseUnit(fromUnit.convertToBaseUnit(value));
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        return new QuantityWeight(convert(value, unit, targetUnit), targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        Objects.requireNonNull(other, "other must not be null");
        Objects.requireNonNull(targetUnit, "targetUnit must not be null");
        double sumInBaseUnit = baseValue() + other.baseValue();
        return new QuantityWeight(targetUnit.convertFromBaseUnit(sumInBaseUnit), targetUnit);
    }

    private double baseValue() {
        return unit.convertToBaseUnit(value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QuantityWeight)) {
            return false;
        }
        QuantityWeight that = (QuantityWeight) other;
        return Math.abs(baseValue() - that.baseValue()) <= EPSILON;
    }

    @Override
    public int hashCode() {
        return QuantityWeight.class.hashCode();
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
