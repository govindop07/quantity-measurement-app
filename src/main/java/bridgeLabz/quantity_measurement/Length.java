package bridgeLabz.quantity_measurement;

public class Length {

	private final double value;
	private final LengthUnit unit;

	public Length(double value, LengthUnit unit) {
		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public Length convertTo(LengthUnit targetUnit) {
		double base = convertToBaseUnit();
		double converted = targetUnit.convertFromBaseUnit(base);
		return new Length(converted, targetUnit);
	}

	public Length add(Length other) {
		double sum = this.convertToBaseUnit() + other.convertToBaseUnit();
		double result = unit.convertFromBaseUnit(sum);
		return new Length(result, unit);
	}

	public Length add(Length other, LengthUnit targetUnit) {
		double sum = this.convertToBaseUnit() + other.convertToBaseUnit();
		double result = targetUnit.convertFromBaseUnit(sum);
		return new Length(result, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Length other = (Length) obj;

		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(convertToBaseUnit()).hashCode();
	}
}