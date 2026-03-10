package bridgeLabz.quantity_measurement;

public enum LengthUnit implements IMeasurable {

	FEET(12.0), INCHES(1.0), YARDS(36.0), CENTIMETERS(0.393701);

	private final double conversionFactor;

	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public double getConversionFactor() {
		return conversionFactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return value * conversionFactor;
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return Math.round((baseValue / conversionFactor) * 100.0) / 100.0;
	}

	@Override
	public String getUnitName() {
		return this.name();
	}
}