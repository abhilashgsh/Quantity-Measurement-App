package com.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityLengthTest {
    private static final double DELTA = 0.000000001;

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

    @Test
    void givenLengthValue_whenConverted_thenReturnsExpectedTargetValue() {
        assertEquals(12.0, QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), DELTA);
        assertEquals(3.0, QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET), DELTA);
        assertEquals(2.54, QuantityLength.convert(1.0, LengthUnit.INCHES, LengthUnit.CENTIMETERS), DELTA);
        assertEquals(36.0, QuantityLength.convert(91.44, LengthUnit.CENTIMETERS, LengthUnit.INCHES), DELTA);
    }

    @Test
    void givenLength_whenConvertedWithInstanceMethod_thenReturnsQuantityInTargetUnit() {
        QuantityLength converted = new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), converted);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
        assertEquals(12.0, converted.getValue(), DELTA);
    }

    @Test
    void givenNullUnits_whenConverted_thenThrowsException() {
        assertThrows(NullPointerException.class, () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
        assertThrows(NullPointerException.class, () -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
        assertThrows(NullPointerException.class, () -> new QuantityLength(1.0, null));
    }
}
