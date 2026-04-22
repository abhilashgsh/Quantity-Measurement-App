package com.quantity;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityWeight oneKg = new QuantityWeight(1.0, WeightUnit.KG);

        System.out.println(oneFoot + " equals " + twelveInches + ": " + oneFoot.equals(twelveInches));
        System.out.println(oneFoot + " + " + twelveInches + " = " + oneFoot.add(twelveInches));
        System.out.println(oneKg + " equals " + oneKg.convertTo(WeightUnit.GRAM) + ": " + oneKg.equals(oneKg.convertTo(WeightUnit.GRAM)));
    }
}
