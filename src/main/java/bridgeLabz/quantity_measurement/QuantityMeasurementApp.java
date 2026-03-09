package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}

	public static Length demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

		Length length = new Length(value, from);
		return length.convertTo(to);
	}

	public static Length demonstrateLengthAddition(Length l1, Length l2) {
		return l1.add(l2);
	}

	public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {

		return l1.add(l2, targetUnit);
	}
}