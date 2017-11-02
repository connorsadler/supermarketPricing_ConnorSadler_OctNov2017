package cfs.supermarketpricing.money;

import java.util.Objects;

/**
 * An immutable amount in pounds + pence
 * 
 * I have used separate long values for pounds/pence for accuracy - we don't want to use a floating
 * point value as they are inaccurate
 * I could use BigDecimal instead but pounds + pence is OK for now.
 * cfstodo: I may use BigDecimal for the calculations to make things a bit easier.
 * 
 * See further comments in MonetaryAmount
 */
public class SterlingAmount implements MonetaryAmount {

	private final long pounds;
	private final long pence;
	
	public SterlingAmount(long pounds, long pence) {
		this.pounds = pounds;
		this.pence = pence;
		
		if (pence > 100) {
			throw new IllegalArgumentException("Pence must be less than 100, but we were given: " + pence);
		}
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

	@Override
	public int hashCode() {
		return Objects.hash(pounds, pence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SterlingAmount other = (SterlingAmount) obj;
		if (pence != other.pence)
			return false;
		if (pounds != other.pounds)
			return false;
		return true;
	}
	
	/**
	 * @see cfs.supermarketpricing.money.MonetaryAmount#plus(cfs.supermarketpricing.money.MonetaryAmount)
	 */
	@Override
	public MonetaryAmount plus(MonetaryAmount amount) {
		
		// For the moment we assume only SterlingAmounts can be added together
		// cfstodo: Check for a better way to declare this method
		if (!(amount instanceof SterlingAmount)) {
			throw new IllegalArgumentException("Only a SterlingAmount can be added to a SterlingAmount, but we were given: " + amount);
		}
		SterlingAmount otherSterlingAmount = (SterlingAmount) amount;
		
		long newPounds = this.pounds + otherSterlingAmount.getPounds();
		long newPence = this.pence + otherSterlingAmount.getPence();
		if (newPence > 100) {
			newPounds++;
			newPence -= 100;
		}
		return new SterlingAmount(newPounds, newPence);
	}
	
	
	
}
