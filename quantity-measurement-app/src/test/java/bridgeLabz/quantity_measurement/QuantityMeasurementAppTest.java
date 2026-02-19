package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	// ---------- Same unit equality ----------

	@Test
	public void testFeetEquality() {
		assertTrue(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(1.0, Length.LengthUnit.FEET)));
	}

	@Test
	public void testInchesEquality() {
		assertTrue(new Length(1.0, Length.LengthUnit.INCHES).equals(new Length(1.0, Length.LengthUnit.INCHES)));
	}

	@Test
	public void testYardEquality() {
		assertTrue(new Length(2.0, Length.LengthUnit.YARDS).equals(new Length(2.0, Length.LengthUnit.YARDS)));
	}

	@Test
	public void testCentimeterEquality() {
		assertTrue(
				new Length(5.0, Length.LengthUnit.CENTIMETERS).equals(new Length(5.0, Length.LengthUnit.CENTIMETERS)));
	}

	// ---------- Cross-unit equality ----------

	@Test
	public void testFeetInchesComparison() {
		assertTrue(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(12.0, Length.LengthUnit.INCHES)));
	}

	@Test
	public void testYardFeetComparison() {
		assertTrue(new Length(1.0, Length.LengthUnit.YARDS).equals(new Length(3.0, Length.LengthUnit.FEET)));
	}

	@Test
	public void testYardInchesComparison() {
		assertTrue(new Length(1.0, Length.LengthUnit.YARDS).equals(new Length(36.0, Length.LengthUnit.INCHES)));
	}

	@Test
	public void testCentimeterInchesComparison() {
		assertTrue(
				new Length(1.0, Length.LengthUnit.CENTIMETERS).equals(new Length(0.393701, Length.LengthUnit.INCHES)));
	}

	// ---------- Inequality cases ----------

	@Test
	public void testFeetInequality() {
		assertFalse(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(2.0, Length.LengthUnit.FEET)));
	}

	@Test
	public void testInchesInequality() {
		assertFalse(new Length(1.0, Length.LengthUnit.INCHES).equals(new Length(2.0, Length.LengthUnit.INCHES)));
	}

	@Test
	public void testYardFeetNonEquivalent() {
		assertFalse(new Length(1.0, Length.LengthUnit.YARDS).equals(new Length(2.0, Length.LengthUnit.FEET)));
	}

	@Test
	public void testCentimeterFeetNonEquivalent() {
		assertFalse(new Length(1.0, Length.LengthUnit.CENTIMETERS).equals(new Length(1.0, Length.LengthUnit.FEET)));
	}

	// ---------- Equality contract ----------

	@Test
	public void testSameReference() {
		Length l = new Length(1.0, Length.LengthUnit.FEET);
		assertTrue(l.equals(l));
	}

	@Test
	public void testNullComparison() {
		Length l = new Length(1.0, Length.LengthUnit.FEET);
		assertFalse(l.equals(null));
	}

	@Test
	public void testTransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}
}