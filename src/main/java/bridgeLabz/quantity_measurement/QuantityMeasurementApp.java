package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	// Demonstrates equality check.
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		return length1.equals(length2);
	}

	// Demonstrates comparison using raw values.
	public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2,
			Length.LengthUnit unit2) {

		Length l1 = new Length(value1, unit1);
		Length l2 = new Length(value2, unit2);

		return demonstrateLengthEquality(l1, l2);
	}

	// Static numeric conversion API
	public static double demonstrateLengthConversion(double value, Length.LengthUnit fromUnit,
			Length.LengthUnit toUnit) {

		return Length.convert(value, fromUnit, toUnit);
	}

	// Overloaded conversion method using Length instance
	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {

		return length.convertTo(toUnit);
	}

	// Main method for standalone testing
	public static void main(String[] args) {

		System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)); // 12.0

		System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET)); // 9.0

		System.out.println(demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS)); // 1.0
	}
}
