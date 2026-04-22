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

    @Test
    void givenSameLengthReference_whenCompared_thenReturnsTrue() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(length.equals(length));
    }

    @Test
    void givenLength_whenComparedWithNullOrDifferentType_thenReturnsFalse() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(length.equals(null));
        assertFalse(length.equals("1 foot"));
    }

    @Test
    void givenSameAndDifferentLengthUnits_whenCompared_thenUsesNormalizedEquality() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET));
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
        assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(13.0, LengthUnit.INCHES));
    }

    @Test
    void givenYardsAndCentimeters_whenEquivalent_thenTheyAreEqualAcrossUnits() {
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET));
        assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.54, LengthUnit.CENTIMETERS));
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(91.44, LengthUnit.CENTIMETERS));
    }

    @Test
    void givenYardsAndCentimeters_whenNotEquivalent_thenTheyAreNotEqual() {
        assertNotEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(100.0, LengthUnit.CENTIMETERS));
        assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARDS));
    }
}
