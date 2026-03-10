package bridgeLabz.quantity_measurement;

public enum VolumeUnit implements IMeasurable {

	LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);

	private final double conversionFactor;

	VolumeUnit(double conversionFactor) {
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
		return Math.round((baseValue / conversionFactor) * 100000.0) / 100000.0;
	}

	@Override
	public String getUnitName() {
		return this.name();
	}
}
