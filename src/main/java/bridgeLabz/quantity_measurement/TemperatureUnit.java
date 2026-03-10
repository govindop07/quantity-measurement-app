package bridgeLabz.quantity_measurement;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

	CELSIUS(false), FAHRENHEIT(true);

	private final Function<Double, Double> conversionValue;

	// Temperature does NOT support arithmetic
	SupportsArithmetic supportsArithmetic = () -> false;

	TemperatureUnit(boolean isFahrenheit) {

		if (isFahrenheit) {
			conversionValue = (f) -> (f - 32) * 5 / 9;
		} else {
			conversionValue = (c) -> c;
		}
	}

	@Override
	public String getUnitName() {
		return this.name();
	}

	@Override
	public double getConversionFactor() {
		return 1.0;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return conversionValue.apply(value);
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {

		if (this == CELSIUS)
			return baseValue;

		return (baseValue * 9 / 5) + 32;
	}

	public double convertTo(double value, TemperatureUnit targetUnit) {

		double base = convertToBaseUnit(value);
		return targetUnit.convertFromBaseUnit(base);
	}

	@Override
	public boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	@Override
	public void validateOperationSupport(String operation) {

		if (!supportsArithmetic.isSupported()) {

			throw new UnsupportedOperationException(this.name() + " does not support " + operation + " operations.");
		}
	}
}