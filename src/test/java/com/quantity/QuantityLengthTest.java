package com.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityLengthTest {
    @Test
    void givenSameFeetReference_whenCompared_thenReturnsTrue() {
        Feet feet = new Feet(1.0);

        assertTrue(feet.equals(feet));
    }

    @Test
    void givenFeet_whenComparedWithNullOrDifferentType_thenReturnsFalse() {
        Feet feet = new Feet(1.0);

        assertFalse(feet.equals(null));
        assertFalse(feet.equals("1 foot"));
    }

    @Test
    void givenEqualFeetValues_whenCompared_thenReturnsTrueUsingDoubleCompare() {
        assertEquals(new Feet(1.0), new Feet(1.0));
        assertEquals(new Feet(-0.0), new Feet(-0.0));
        assertNotEquals(new Feet(-0.0), new Feet(0.0));
    }

    @Test
    void givenFeetWithDifferentValues_whenCompared_thenReturnsFalse() {
        assertNotEquals(new Feet(1.0), new Feet(2.0));
    }

    @Test
    void givenInches_whenComparedWithNullDifferentTypeOrSameReference_thenBehavesSafely() {
        Inches inches = new Inches(12.0);

        assertTrue(inches.equals(inches));
        assertFalse(inches.equals(null));
        assertFalse(inches.equals(new Feet(1.0)));
        assertEquals(inches, new Inches(12.0));
        assertNotEquals(inches, new Inches(13.0));
    }

    @Test
    void givenLegacyFeetAndInches_whenNumericallyEquivalent_thenTheyAreNotEqual() {
        assertNotEquals(new Feet(1.0), new Inches(12.0));
    }
}
