package bridgeLabz.quantity_measurement;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {

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

	public U getUnit() {
		return unit;
	}

	private double toBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public Quantity<U> convertTo(U targetUnit) {

		double base = unit.convertToBaseUnit(value);
		double converted = targetUnit.convertFromBaseUnit(base);

		return new Quantity<>(converted, targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {

		double sum = this.toBaseUnit() + other.toBaseUnit();
		double result = unit.convertFromBaseUnit(sum);

		return new Quantity<>(result, unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		double sum = this.toBaseUnit() + other.toBaseUnit();
		double result = targetUnit.convertFromBaseUnit(sum);

		return new Quantity<>(result, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Quantity<?> other))
			return false;

		if (this.unit.getClass() != other.unit.getClass())
			return false;

		double base1 = this.unit.convertToBaseUnit(this.value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return Double.compare(base1, base2) == 0;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(toBaseUnit()).hashCode();
	}

	@Override
	public String toString() {
		return value + " " + unit.getUnitName();
	}
}