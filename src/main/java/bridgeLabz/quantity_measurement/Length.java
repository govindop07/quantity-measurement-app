package bridgeLabz.quantity_measurement;

public class Length {

	private double value;
	private LengthUnit unit;

	public Length(double value, LengthUnit unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (Double.isNaN(value) || Double.isInfinite(value))
			throw new IllegalArgumentException("Invalid value");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Length))
			return false;

		Length that = (Length) obj;

		double thisBase = this.unit.convertToBaseUnit(this.value);
		double thatBase = that.unit.convertToBaseUnit(that.value);

		return Math.abs(thisBase - thatBase) < 0.01;
	}

	public Length convertTo(LengthUnit targetUnit) {

		double baseValue = unit.convertToBaseUnit(value);

		double converted = targetUnit.convertFromBaseUnit(baseValue);

		return new Length(converted, targetUnit);
	}

	public Length add(Length that) {

		double base1 = this.unit.convertToBaseUnit(this.value);
		double base2 = that.unit.convertToBaseUnit(that.value);

		double sum = base1 + base2;

		double result = this.unit.convertFromBaseUnit(sum);

		return new Length(result, this.unit);
	}

	public Length add(Length that, LengthUnit targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double base1 = this.unit.convertToBaseUnit(this.value);
		double base2 = that.unit.convertToBaseUnit(that.value);

		double sum = base1 + base2;

		double result = targetUnit.convertFromBaseUnit(sum);

		return new Length(result, targetUnit);
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}
}