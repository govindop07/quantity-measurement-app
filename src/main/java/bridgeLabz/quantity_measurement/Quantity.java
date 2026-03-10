package bridgeLabz.quantity_measurement;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	// =========================
	// EQUALITY
	// =========================

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Quantity<?> other))
			return false;

		if (!unit.getClass().equals(other.unit.getClass()))
			return false;

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return Math.abs(base1 - base2) < 0.01;
	}

	// =========================
	// CONVERSION
	// =========================

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		// Special handling for temperature
		if (unit instanceof TemperatureUnit tempUnit && targetUnit instanceof TemperatureUnit targetTemp) {

			double converted = tempUnit.convertTo(value, targetTemp);

			return new Quantity<>(converted, targetUnit);
		}

		double base = unit.convertToBaseUnit(value);
		double converted = targetUnit.convertFromBaseUnit(base);

		return new Quantity<>(converted, targetUnit);
	}

	// =========================
	// ADD
	// =========================

	public Quantity<U> add(Quantity<U> other) {
		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		unit.validateOperationSupport("addition");

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		double result = base1 + base2;

		return new Quantity<>(targetUnit.convertFromBaseUnit(result), targetUnit);
	}

	// =========================
	// SUBTRACT
	// =========================

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		unit.validateOperationSupport("subtraction");

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		double result = base1 - base2;

		return new Quantity<>(targetUnit.convertFromBaseUnit(result), targetUnit);
	}

	// =========================
	// DIVIDE
	// =========================

	public double divide(Quantity<U> other) {

		unit.validateOperationSupport("division");

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return base1 / base2;
	}
}