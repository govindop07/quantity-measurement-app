package bridgeLabz.quantity_measurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	private static final double EPSILON = 0.0001;

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

	private double toBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	// equality

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

		return Math.abs(base1 - base2) < EPSILON;
	}

	@Override
	public String toString() {
		return value + " " + unit.getUnitName();
	}

	// conversion

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double base = toBaseUnit();
		double converted = targetUnit.convertFromBaseUnit(base);

		return new Quantity<>(converted, targetUnit);
	}

	// add

	public Quantity<U> add(Quantity<U> other) {
		return add(other, unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double resultBase = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);

		double converted = targetUnit.convertFromBaseUnit(resultBase);

		return new Quantity<>(roundToTwoDecimals(converted), targetUnit);
	}

	// subtract

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double resultBase = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);

		double converted = targetUnit.convertFromBaseUnit(resultBase);

		return new Quantity<>(roundToTwoDecimals(converted), targetUnit);
	}

	// divide

	public double divide(Quantity<U> other) {

		validateArithmeticOperands(other, null, false);

		return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
	}

	// validation

	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

		if (other == null)
			throw new IllegalArgumentException("Other quantity cannot be null");

		if (!unit.getClass().equals(other.unit.getClass()))
			throw new IllegalArgumentException("Incompatible unit types");

		if (!Double.isFinite(value) || !Double.isFinite(other.value))
			throw new IllegalArgumentException("Values must be finite");

		if (targetUnitRequired && targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");
	}

	private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {

		double base1 = this.toBaseUnit();
		double base2 = other.toBaseUnit();

		return operation.compute(base1, base2);
	}

	// rounding

	private double roundToTwoDecimals(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	private enum ArithmeticOperation {

		ADD((a, b) -> a + b),

		SUBTRACT((a, b) -> a - b),

		DIVIDE((a, b) -> {
			if (b == 0.0)
				throw new ArithmeticException("Cannot divide by zero");
			return a / b;
		});

		private final DoubleBinaryOperator operation;

		ArithmeticOperation(DoubleBinaryOperator operation) {
			this.operation = operation;
		}

		public double compute(double a, double b) {
			return operation.applyAsDouble(a, b);
		}
	}
}