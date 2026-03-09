package bridgeLabz.quantity_measurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	@Test
	public void kilogramEquals1000Grams() {

		Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000, WeightUnit.GRAM);

		assertEquals(w1, w2);
	}

	@Test
	public void poundEquals453Point592Grams() {

		Weight w1 = new Weight(1, WeightUnit.POUND);
		Weight w2 = new Weight(453.592, WeightUnit.GRAM);

		assertEquals(w1, w2);
	}

	@Test
	public void kilogramNotEqualToPound() {

		Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1, WeightUnit.POUND);

		assertNotEquals(w1, w2);
	}

	@Test
	public void convertKilogramToGram() {

		Weight result = new Weight(1, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);

		assertEquals(new Weight(1000, WeightUnit.GRAM), result);
	}

	@Test
	public void additionOfWeightsEqualsExpected() {

		Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000, WeightUnit.GRAM);

		Weight result = w1.add(w2);

		assertEquals(new Weight(2, WeightUnit.KILOGRAM), result);
	}
}