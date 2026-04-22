package com.quantity;

import java.util.Objects;

public final class Inches {
    private final double value;

    public Inches(double value) {
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
        if (!(other instanceof Inches)) {
            return false;
        }
        Inches inches = (Inches) other;
        return Double.compare(value, inches.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " INCHES";
    }
}
