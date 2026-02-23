package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.01;

	// UC6 Tests (Implicit Target Unit)

	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_Commutativity_UC6() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length r1 = l1.add(l2);
		Length r2 = l2.add(l1);

		assertEquals(r1.convertTo(Length.LengthUnit.INCHES), r2.convertTo(Length.LengthUnit.INCHES));
	}

	// UC7 Tests (Explicit Target Unit)

	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.FEET);

		assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.INCHES);

		assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.YARDS);

		assertEquals(0.67, result.getValue(), EPSILON);
		assertEquals(Length.LengthUnit.YARDS, result.getUnit());
	}

	@Test
	public void testAddition_Commutativity_UC7() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length r1 = l1.add(l2, Length.LengthUnit.INCHES);
		Length r2 = l2.add(l1, Length.LengthUnit.INCHES);

		assertEquals(r1, r2);
	}

	@Test
	public void testAddition_WithZero_TargetUnit() {

		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.YARDS);

		assertEquals(1.67, result.getValue(), EPSILON);
		assertEquals(Length.LengthUnit.YARDS, result.getUnit());
	}

	@Test
	public void testAddition_NegativeValues_TargetUnit() {

		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

		Length result = l1.add(l2, Length.LengthUnit.INCHES);

		assertEquals(36.0, result.getValue(), EPSILON);
		assertEquals(Length.LengthUnit.INCHES, result.getUnit());
	}

	@Test
	public void testAddition_NullTargetUnit() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		assertThrows(IllegalArgumentException.class, () -> l1.add(l2, null));
	}
}