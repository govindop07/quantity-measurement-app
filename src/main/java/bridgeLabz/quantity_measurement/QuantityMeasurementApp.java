package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	// Equality demonstration
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		return length1.equals(length2);
	}

	// Comparison using raw values
	public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2,
			Length.LengthUnit unit2) {

		Length l1 = new Length(value1, unit1);
		Length l2 = new Length(value2, unit2);

		return demonstrateLengthEquality(l1, l2);
	}

	// Conversion using raw values (UC5)
	public static double demonstrateLengthConversion(double value, Length.LengthUnit fromUnit,
			Length.LengthUnit toUnit) {

		return Length.convert(value, fromUnit, toUnit);
	}

	// Conversion using Length object
	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {

		return length.convertTo(toUnit);
	}

	// UC6: Addition demonstration
	public static Length demonstrateLengthAddition(Length length1, Length length2) {

		if (length1 == null || length2 == null) {
			throw new IllegalArgumentException("Lengths cannot be null.");
		}

		return length1.add(length2);
	}

	// Main method
	public static void main(String[] args) {

		Length length1 = new Length(1.0, Length.LengthUnit.FEET);
		Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = demonstrateLengthAddition(length1, length2);

		System.out.println("Result: " + result);
	}
}
