package bridgeLabz.quantity_measurement;

@FunctionalInterface
interface SupportsArithmetic {
	boolean isSupported();
}

public interface IMeasurable {

	SupportsArithmetic supportsArithmetic = () -> true;

	// Mandatory conversion methods
	String getUnitName();

	double getConversionFactor();

	double convertToBaseUnit(double value);

	double convertFromBaseUnit(double baseValue);

	// Optional support check
	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	// Default validation (do nothing)
	default void validateOperationSupport(String operation) {
	}
}