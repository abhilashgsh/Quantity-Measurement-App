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
}
