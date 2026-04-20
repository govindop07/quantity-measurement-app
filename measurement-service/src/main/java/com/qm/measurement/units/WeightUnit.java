package com.qm.measurement.units;

import com.qm.measurement.core.IMeasurable;

public enum WeightUnit implements IMeasurable {

    GRAM(1.0),
    MILLIGRAM(1.0 / 1000.0),
    KILOGRAM(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double toBaseUnit(double value) {
        return value * conversionFactor;
    }
    public double fromBaseUnit(double value) {
        return value / conversionFactor;
    }

    public static WeightUnit fromString(String unit) {
        return WeightUnit.valueOf(unit.trim().toUpperCase());
    }
}
