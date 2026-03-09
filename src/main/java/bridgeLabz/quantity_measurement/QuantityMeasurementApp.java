package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

		return q1.equals(q2);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {

		return quantity.convertTo(targetUnit);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {

		return q1.add(q2);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2,
			U targetUnit) {

		return q1.add(q2, targetUnit);
	}

	public static void main(String[] args) {

		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("Length Equal: " + demonstrateEquality(length1, length2));

		Quantity<LengthUnit> converted = demonstrateConversion(length1, LengthUnit.INCHES);

		System.out.println("Converted: " + converted);

		Quantity<LengthUnit> sum = demonstrateAddition(length1, length2, LengthUnit.FEET);

		System.out.println("Sum: " + sum);

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Weight Equal: " + demonstrateEquality(w1, w2));

		Quantity<WeightUnit> weightSum = demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

		System.out.println("Weight Sum: " + weightSum);
	}
}