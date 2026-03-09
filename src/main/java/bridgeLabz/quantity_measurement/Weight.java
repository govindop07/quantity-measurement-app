package bridgeLabz.quantity_measurement;

public class Weight {

	private final double value;
	private final WeightUnit unit;

	public Weight(double value, WeightUnit unit) {

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

	public WeightUnit getUnit() {
		return unit;
	}

	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public Weight convertTo(WeightUnit targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double base = convertToBaseUnit();
		double converted = targetUnit.convertFromBaseUnit(base);

		return new Weight(converted, targetUnit);
	}

	public Weight add(Weight other) {

		double sum = this.convertToBaseUnit() + other.convertToBaseUnit();
		double result = unit.convertFromBaseUnit(sum);

		return new Weight(result, unit);
	}

	public Weight add(Weight other, WeightUnit targetUnit) {

		double sum = this.convertToBaseUnit() + other.convertToBaseUnit();
		double result = targetUnit.convertFromBaseUnit(sum);

		return new Weight(result, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Weight other = (Weight) obj;

		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(convertToBaseUnit()).hashCode();
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}