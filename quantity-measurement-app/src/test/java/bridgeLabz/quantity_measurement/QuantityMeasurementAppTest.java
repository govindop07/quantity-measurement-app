package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bridgeLabz.quantity_measurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

	@Test
	public void testFeetEquality_SameValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);

		assertTrue(f1.equals(f2));
	}

	@Test
	public void testFeetEquality_DifferentValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(2.0);

		assertFalse(f1.equals(f2));
	}

	@Test
	public void testFeetEquality_NullComparison() {
		Feet f1 = new Feet(1.0);

		assertFalse(f1.equals(null));
	}

	@Test
	public void testFeetEquality_DifferentClass() {
		Feet f1 = new Feet(1.0);
		Object obj = new Object();

		assertFalse(f1.equals(obj));
	}

	@Test
	public void testFeetEquality_SameReference() {
		Feet f1 = new Feet(1.0);

		assertTrue(f1.equals(f1));
	}
}