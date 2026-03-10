package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.01;

	// LENGTH TESTS

	@Test
	public void lengthFeetEqualsInches() {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	public void convertLengthFeetToInches() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = length.convertTo(LengthUnit.INCHES);

		assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
	}

	@Test
	public void addLengthFeetAndInches() {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = l1.add(l2, LengthUnit.FEET);

		assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	}

	// WEIGHT TESTS

	@Test
	public void weightKilogramEqualsGrams() {

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(w1.equals(w2));
	}

	@Test
	public void convertWeightKilogramsToGrams() {

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = weight.convertTo(WeightUnit.GRAM);

		assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
	}

	@Test
	public void addWeightKilogramsAndGrams() {

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = w1.add(w2, WeightUnit.KILOGRAM);

		assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
	}

	// VOLUME TESTS (UC11)

	@Test
	public void volumeLiterEqualsMilliliters() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void volumeGallonEqualsLiters() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void convertVolumeLiterToMilliliters() {

		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
	}

	@Test
	public void convertVolumeGallonToLiter() {

		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> result = volume.convertTo(VolumeUnit.LITRE);

		assertEquals(3.78541, result.getValue(), EPSILON);
	}

	@Test
	public void addVolumeLiterAndMilliliter() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.LITRE);

		assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
	}

	@Test
	public void addVolumeLitersAndGallons() {

		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	// CROSS CATEGORY SAFETY

	@Test
	public void preventCrossCategoryComparison() {

		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertFalse(volume.equals(length));
	}
}