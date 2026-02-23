package bridgeLabz.quantity_measurement;

public class Length {

	// Value and unit (immutable)
	private final double value;
	private final LengthUnit unit;

	// Base unit = INCHES
	public enum LengthUnit {

		FEET(12.0), INCHES(1.0), YARDS(36.0), CENTIMETERS(0.393701);

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

	// Convert raw value between units (UC5)
	public static double convert(double value, LengthUnit from, LengthUnit to) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (from == null || to == null) {
			throw new IllegalArgumentException("Units cannot be null.");
		}

		double base = value * from.getConversionFactor();
		double result = base / to.getConversionFactor();

		return round(result);
	}

	// Convert this object
	public Length convertTo(LengthUnit targetUnit) {
		double converted = convert(this.value, this.unit, targetUnit);
		return new Length(converted, targetUnit);
	}

	// Convert to base unit (inches)
	private double toBaseUnit() {
		return value * unit.getConversionFactor();
	}

	// Common private utility for UC6 & UC7
	private Length addAndConvert(Length other, LengthUnit targetUnit) {

		if (other == null) {
			throw new IllegalArgumentException("Length to add cannot be null.");
		}

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null.");
		}

		double base1 = this.toBaseUnit();
		double base2 = other.toBaseUnit();

		double sumBase = base1 + base2;

		double finalValue = sumBase / targetUnit.getConversionFactor();

		return new Length(round(finalValue), targetUnit);
	}

	// UC6: add, result in first operand unit
	public Length add(Length other) {
		return addAndConvert(other, this.unit);
	}

	// UC7: add with explicit target unit
	public Length add(Length other, LengthUnit targetUnit) {
		return addAndConvert(other, targetUnit);
	}

	// Equality (based on base unit)
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Length other = (Length) o;

		return Double.compare(round(this.toBaseUnit()), round(other.toBaseUnit())) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(round(toBaseUnit()));
	}

	private static double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}

	public double getValue() {
		return value;
	}

	public LengthUnit getUnit() {
		return unit;
	}
}