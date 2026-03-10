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

	public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2) {

		return q1.subtract(q2);
	}

	public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2,
			U targetUnit) {

		return q1.subtract(q2, targetUnit);
	}

	public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {

		return q1.divide(q2);
	}

	public static void main(String[] args) {

		Quantity<LengthUnit> length1 = new Quantity<>(10, LengthUnit.FEET);

		Quantity<LengthUnit> length2 = new Quantity<>(6, LengthUnit.INCHES);

		System.out.println("Addition: " + demonstrateAddition(length1, length2));

		System.out.println("Subtraction: " + demonstrateSubtraction(length1, length2));

		System.out.println("Division: " + demonstrateDivision(length1, length2));

		Quantity<VolumeUnit> volume1 = new Quantity<>(5, VolumeUnit.LITRE);

		Quantity<VolumeUnit> volume2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

		System.out.println("Volume Subtraction: " + demonstrateSubtraction(volume1, volume2));
	}
}