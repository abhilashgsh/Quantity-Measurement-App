package com.quantity;

import java.util.Objects;

public final class Feet {
    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Feet)) {
            return false;
        }
        Feet feet = (Feet) other;
        return Double.compare(value, feet.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " FEET";
    }
}
