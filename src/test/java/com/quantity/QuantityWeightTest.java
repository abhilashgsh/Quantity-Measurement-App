package com.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityWeightTest {
    private static final double DELTA = 0.000000001;

    @Test
    void givenSameWeightReference_whenCompared_thenReturnsTrue() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KG);

        assertTrue(weight.equals(weight));
    }

    @Test
    void givenWeight_whenComparedWithNullDifferentTypeOrLength_thenReturnsFalse() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KG);

        assertFalse(weight.equals(null));
        assertFalse(weight.equals("1 kg"));
        assertFalse(weight.equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void givenSameAndDifferentWeightUnits_whenCompared_thenUsesNormalizedEquality() {
        assertEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(1.0, WeightUnit.POUND), new QuantityWeight(0.45359237, WeightUnit.KG));
        assertEquals(new QuantityWeight(1.0, WeightUnit.POUND), new QuantityWeight(453.59237, WeightUnit.GRAM));
        assertNotEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(999.0, WeightUnit.GRAM));
    }

    @Test
    void givenWeightValue_whenConverted_thenReturnsExpectedTargetValue() {
        assertEquals(1000.0, QuantityWeight.convert(1.0, WeightUnit.KG, WeightUnit.GRAM), DELTA);
        assertEquals(1.0, QuantityWeight.convert(1000.0, WeightUnit.GRAM, WeightUnit.KG), DELTA);
        assertEquals(0.45359237, QuantityWeight.convert(1.0, WeightUnit.POUND, WeightUnit.KG), DELTA);
        assertEquals(453.59237, QuantityWeight.convert(1.0, WeightUnit.POUND, WeightUnit.GRAM), DELTA);
    }

    @Test
    void givenWeightValue_whenUnitConvertsThroughBaseMethods_thenReturnsExpectedValues() {
        assertEquals(1.0, WeightUnit.KG.convertToBaseUnit(1.0), DELTA);
        assertEquals(1.0, WeightUnit.KG.convertFromBaseUnit(1.0), DELTA);
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), DELTA);
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), DELTA);
        assertEquals(0.45359237, WeightUnit.POUND.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    void givenWeight_whenConvertedWithInstanceMethod_thenReturnsQuantityInTargetUnit() {
        QuantityWeight converted = new QuantityWeight(1.0, WeightUnit.KG).convertTo(WeightUnit.GRAM);

        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), converted);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
        assertEquals(1000.0, converted.getValue(), DELTA);
    }

    @Test
    void givenSameAndDifferentWeightUnits_whenAdded_thenReturnsExpectedUnitsAndValues() {
        QuantityWeight sameUnit = new QuantityWeight(1.0, WeightUnit.KG)
                .add(new QuantityWeight(2.0, WeightUnit.KG));
        QuantityWeight crossUnit = new QuantityWeight(1.0, WeightUnit.KG)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        QuantityWeight targetUnit = new QuantityWeight(1.0, WeightUnit.KG)
                .add(new QuantityWeight(1.0, WeightUnit.POUND), WeightUnit.GRAM);

        assertEquals(WeightUnit.KG, sameUnit.getUnit());
        assertEquals(3.0, sameUnit.getValue(), DELTA);
        assertEquals(WeightUnit.KG, crossUnit.getUnit());
        assertEquals(2.0, crossUnit.getValue(), DELTA);
        assertEquals(WeightUnit.GRAM, targetUnit.getUnit());
        assertEquals(1453.59237, targetUnit.getValue(), DELTA);
    }

    @Test
    void givenZeroNegativeAndLargeWeightValues_whenComparedConvertedAndAdded_thenWorks() {
        assertEquals(new QuantityWeight(0.0, WeightUnit.KG), new QuantityWeight(0.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(-1.0, WeightUnit.KG), new QuantityWeight(-1000.0, WeightUnit.GRAM));
        assertEquals(1_000_000_000.0, QuantityWeight.convert(1_000_000.0, WeightUnit.KG, WeightUnit.GRAM), DELTA);

        QuantityWeight result = new QuantityWeight(-1.0, WeightUnit.KG)
                .add(new QuantityWeight(2500.0, WeightUnit.GRAM));

        assertEquals(1.5, result.getValue(), DELTA);
        assertEquals(WeightUnit.KG, result.getUnit());
    }

    @Test
    void givenNullUnitsOrOperands_whenUsed_thenThrowsException() {
        assertThrows(NullPointerException.class, () -> new QuantityWeight(1.0, null));
        assertThrows(NullPointerException.class, () -> QuantityWeight.convert(1.0, null, WeightUnit.KG));
        assertThrows(NullPointerException.class, () -> QuantityWeight.convert(1.0, WeightUnit.KG, null));
        assertThrows(NullPointerException.class, () -> new QuantityWeight(1.0, WeightUnit.KG).add(null));
        assertThrows(NullPointerException.class, () -> new QuantityWeight(1.0, WeightUnit.KG).add(new QuantityWeight(1.0, WeightUnit.GRAM), null));
    }

    @Test
    void givenValidWeightConstruction_whenCreated_thenDoesNotThrow() {
        assertDoesNotThrow(() -> new QuantityWeight(1.0, WeightUnit.KG));
    }
}
