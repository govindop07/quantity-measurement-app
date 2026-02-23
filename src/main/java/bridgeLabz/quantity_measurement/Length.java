package bridgeLabz.quantity_measurement;

public class Length {

	// Instance variables
	private final double value;
	private final LengthUnit unit;

	// Enum representing supported length units
	// Conversion factor is relative to base unit (INCHES)
	public enum LengthUnit {

		FEET(12.0), // 1 foot = 12 inches
		INCHES(1.0), // Base unit
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

	// Static conversion API (UC5)
	public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (sourceUnit == null || targetUnit == null) {
			throw new IllegalArgumentException("Units must not be null.");
		}

		// Convert to base unit (inches)
		double baseValue = value * sourceUnit.getConversionFactor();

		// Convert to target unit
		double converted = baseValue / targetUnit.getConversionFactor();

		// Round to 2 decimal places
		return Math.round(converted * 100.0) / 100.0;
	}

	// Convert current object to target unit
	public Length convertTo(LengthUnit targetUnit) {

		double convertedValue = convert(this.value, this.unit, targetUnit);

		return new Length(convertedValue, targetUnit);
	}

	// Convert to base unit internally (inches)
	private double convertToBaseUnit() {
		double baseValue = value * unit.getConversionFactor();
		return Math.round(baseValue * 100.0) / 100.0;
	}

	// Equality check
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

	// UC6: Add another length
	// Result is returned in the unit of the first operand
	public Length add(Length thatLength) {

		if (thatLength == null) {
			throw new IllegalArgumentException("Length to add cannot be null.");
		}

		// Convert both to base unit (inches)
		double base1 = this.convertToBaseUnit();
		double base2 = thatLength.convertToBaseUnit();

		// Add
		double sumInBase = base1 + base2;

		// Convert sum back to unit of first operand
		double finalValue = sumInBase / this.unit.getConversionFactor();

		finalValue = Math.round(finalValue * 100.0) / 100.0;

		return new Length(finalValue, this.unit);
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}
