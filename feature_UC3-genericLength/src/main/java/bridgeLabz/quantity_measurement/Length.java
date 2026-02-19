package bridgeLabz.quantity_measurement;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;

	// Enum representing supported units and conversion factors to base unit
	// (inches)
	public enum LengthUnit {
		FEET(12.0), INCHES(1.0);

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
		this.value = value;
		this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
	}

	// Convert to base unit (inches)
	private double toBaseUnit() {
		return value * unit.getConversionFactor();
	}

	// Compare two Length objects
	public boolean compare(Length other) {
		if (other == null)
			return false;
		return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Length))
			return false;
		return compare((Length) o);
	}

	@Override
	public int hashCode() {
		return Double.hashCode(toBaseUnit());
	}
}
