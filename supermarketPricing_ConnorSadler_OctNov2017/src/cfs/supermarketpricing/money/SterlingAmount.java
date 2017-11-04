package cfs.supermarketpricing.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * An immutable amount in pounds + pence
 * 
 * I have used separate long values for pounds/pence for accuracy - we don't want to use a floating
 * point value as they are inaccurate
 * I could use BigDecimal instead but pounds + pence is OK for now.
 * NOTE: I have used BigDecimal for the calculations to make things a bit easier.
 * 
 * See further comments in MonetaryAmount
 */
public class SterlingAmount implements MonetaryAmount {
	private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
	
	private final long pounds;
	private final long pence;
	
	/**
	 * Constructor
	 */
	public SterlingAmount(long pounds, long pence) {
		this.pounds = pounds;
		this.pence = pence;
		
		if (pence > 100) {
			throw new IllegalArgumentException("Pence must be less than 100, but we were given: " + pence);
		}
	}
	
	/**
	 * createFromBigDecimal
	 */
	public static SterlingAmount createFromBigDecimal(BigDecimal bd) {
		BigDecimal fractionalPart = bd.remainder(BigDecimal.ONE);
		BigDecimal integerPart = bd.subtract(fractionalPart);
		long newPounds = integerPart.longValue();
		long newPence = fractionalPart.multiply(ONE_HUNDRED).longValue();
		return new SterlingAmount(newPounds, newPence);
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
	
	/**
	 * toBigDecimal
	 * 
	 * Conv to BigDecimal
	 */
	BigDecimal toBigDecimal() {
		BigDecimal value = new BigDecimal(pounds);
		BigDecimal penceBD = BigDecimal.valueOf(pence).divide(ONE_HUNDRED);
		value = value.add(penceBD);
		return value;
	}

	/**
	 * @see cfs.supermarketpricing.money.MonetaryAmount#times(double)
	 */
	@Override
	public MonetaryAmount times(double d) {
		// Convert into BigDecimal
		BigDecimal value = toBigDecimal();
		
		// Calc
		BigDecimal result = value.multiply(BigDecimal.valueOf(d));
		// Round to nearest penny
		result = result.setScale(2, RoundingMode.HALF_UP);
		
		// Conv back from BigDecimal
		return createFromBigDecimal(result);
	}

	
}
