package com.qm.measurement.core;

public interface IMeasurable {
    double toBaseUnit(double value);
    double fromBaseUnit(double value);
}
