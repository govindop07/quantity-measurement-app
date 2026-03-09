package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	// Length Methods

	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}

	public static Length demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

		return new Length(value, from).convertTo(to);
	}

	public static Length demonstrateLengthAddition(Length l1, Length l2) {
		return l1.add(l2);
	}

	public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit target) {

		return l1.add(l2, target);
	}

	// Weight Methods

	public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
		return w1.equals(w2);
	}

	public static Weight demonstrateWeightConversion(double value, WeightUnit from, WeightUnit to) {

		return new Weight(value, from).convertTo(to);
	}

	public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
		return w1.add(w2);
	}

	public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target) {

		return w1.add(w2, target);
	}
}