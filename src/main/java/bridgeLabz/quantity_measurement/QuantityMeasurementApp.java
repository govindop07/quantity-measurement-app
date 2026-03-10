package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	// Generic equality demonstration
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {

		return quantity1.equals(quantity2);
	}

	// Generic conversion demonstration
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {

		return quantity.convertTo(targetUnit);
	}

	// Generic addition (result in first quantity's unit)
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,
			Quantity<U> quantity2) {

		return quantity1.add(quantity2);
	}

	// Generic addition with explicit target unit
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2,
			U targetUnit) {

		return quantity1.add(quantity2, targetUnit);
	}

	public static void main(String[] args) {

		System.out.println("====== LENGTH OPERATIONS ======");

		Quantity<LengthUnit> lengthFeet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> lengthInches = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("Equality: " + demonstrateEquality(lengthFeet, lengthInches));

		Quantity<LengthUnit> convertedLength = demonstrateConversion(lengthFeet, LengthUnit.INCHES);

		System.out.println("Converted Length: " + convertedLength);

		Quantity<LengthUnit> sumLength = demonstrateAddition(lengthFeet, lengthInches, LengthUnit.FEET);

		System.out.println("Added Length: " + sumLength);

		System.out.println("\n====== WEIGHT OPERATIONS ======");

		Quantity<WeightUnit> weightKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> weightGram = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Equality: " + demonstrateEquality(weightKg, weightGram));

		Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightKg, WeightUnit.GRAM);

		System.out.println("Converted Weight: " + convertedWeight);

		Quantity<WeightUnit> sumWeight = demonstrateAddition(weightKg, weightGram, WeightUnit.KILOGRAM);

		System.out.println("Added Weight: " + sumWeight);

		System.out.println("\n====== VOLUME OPERATIONS (UC11) ======");

		Quantity<VolumeUnit> volumeLiter = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> volumeMilliLiter = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> volumeGallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		System.out.println("Equality (1L vs 1000mL): " + demonstrateEquality(volumeLiter, volumeMilliLiter));

		Quantity<VolumeUnit> convertedVolume = demonstrateConversion(volumeLiter, VolumeUnit.MILLILITRE);

		System.out.println("Converted Volume: " + convertedVolume);

		Quantity<VolumeUnit> volumeSum = demonstrateAddition(volumeLiter, volumeMilliLiter, VolumeUnit.LITRE);

		System.out.println("Added Volume: " + volumeSum);

		Quantity<VolumeUnit> gallonToLiter = demonstrateConversion(volumeGallon, VolumeUnit.LITRE);

		System.out.println("1 Gallon in Litres: " + gallonToLiter);
	}
}