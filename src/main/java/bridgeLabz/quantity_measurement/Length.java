package bridgeLabz.quantity_measurement;

// A generic value object representing a length with unit support. Base unit for conversion is inches.

public class Length {

	private final double value;
	private final LengthUnit unit;

//	 Enum representing supported length units. Conversion factor is defined relative to base unit.

	public enum LengthUnit {

		FEET(12.0), // 1 foot = 12 inches
		INCHES(1.0), // base unit
		YARDS(36.0), // 1 yard = 36 inches
		CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

		private final double conversionFactor;

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}
	}

	// Constructor
	public Length(double value, LengthUnit unit) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null.");
		}

		this.value = value;
		this.unit = unit;
	}

	// Static conversion API

	public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (sourceUnit == null || targetUnit == null) {
			throw new IllegalArgumentException("Units must not be null.");
		}

		// Normalize to base unit (inches)
		double baseValue = value * sourceUnit.getConversionFactor();

		// Convert to target unit
		double converted = baseValue / targetUnit.getConversionFactor();

		// Round to 2 decimal places
		return Math.round(converted * 100.0) / 100.0;
	}

	// Instance conversion method
	public Length convertTo(LengthUnit targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null.");
		}

		double convertedValue = convert(this.value, this.unit, targetUnit);

		return new Length(convertedValue, targetUnit);
	}

	// Convert to base unit (inches) internally for equality comparison
	private double convertToBaseUnit() {
		double baseValue = value * unit.getConversionFactor();
		return Math.round(baseValue * 100.0) / 100.0;
	}

	// Equality comparison
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Length other = (Length) o;

		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(convertToBaseUnit());
	}

	@Override
	public String toString() {
		return String.format("%.2f %s", value, unit);
	}
}
