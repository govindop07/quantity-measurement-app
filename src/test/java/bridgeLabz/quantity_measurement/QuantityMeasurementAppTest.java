package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

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
	public void testAddition_Commutativity() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length r1 = l1.add(l2);
		Length r2 = l2.add(l1);

		assertEquals(r1.convertTo(Length.LengthUnit.INCHES), r2.convertTo(Length.LengthUnit.INCHES));
	}

	@Test
	public void testAddition_WithZero() {

		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_NegativeValues() {

		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

		Length result = l1.add(l2);

		assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_NullSecondOperand() {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> l1.add(null));
	}
}
