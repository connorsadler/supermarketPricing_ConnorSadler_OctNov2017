package cfs.supermarketpricing.money;

/**
 * An immutable amount in pounds + pence
 * 
 * See further comments in MonetaryAmount
 */
public class SterlingAmount implements MonetaryAmount {

	private final long pounds;
	private final long pence;
	
	public SterlingAmount(long pounds, long pence) {
		this.pounds = pounds;
		this.pence = pence;
	}

	public long getPounds() {
		return pounds;
	}

	public long getPence() {
		return pence;
	}

	@Override
	public String toString() {
		return "SterlingAmount [pounds=" + pounds + ", pence=" + pence + "]";
	}
	
	
	
}
