package ga.ferpinan.pricesrestapi.exception;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException() {
		super();
	}

	public PriceNotFoundException(String message) {
		super(message);
	}
}
