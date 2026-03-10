package bridgeLabz.quantity_measurement;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		System.out.println("=== Temperature Demonstration ===");

		Quantity<TemperatureUnit> temp1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> temp2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		System.out.println("0°C equals 32°F : " + temp1.equals(temp2));

		Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> fahrenheit = celsius.convertTo(TemperatureUnit.FAHRENHEIT);

		System.out.println("100°C = " + fahrenheit.getValue() + "°F");

		try {

			celsius.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));

		} catch (UnsupportedOperationException e) {

			System.out.println("Cannot add temperatures: " + e.getMessage());
		}
	}
}