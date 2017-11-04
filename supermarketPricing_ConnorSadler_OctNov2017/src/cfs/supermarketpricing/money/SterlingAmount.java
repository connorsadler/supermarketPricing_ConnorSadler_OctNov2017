package cfs.supermarketpricing.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * An immutable amount in pounds + pence
 * 
 * I have used separate a single long pence value for accuracy - we don't want to use a floating
 * point value as they are inaccurate
 * I could use BigDecimal instead but pence is OK for now.
 * NOTE: I have used BigDecimal for the calculations to make things a bit easier.
 * 
 * See further comments in MonetaryAmount
 */
public class SterlingAmount implements MonetaryAmount {
	private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
	// Store all pounds and pence in the single long value
	// Makes calcs easy and makes representing a -ve number easy
	// e.g. £1.50 = 150 pence
	private final long pence;
	
	/**
	 * Constructor
	 */
	public SterlingAmount(long pounds, long pence) {
		// It can get confusing if we allow construction with (for example) £-1 + 40 pence => that would equal £-0.60
		if ((pounds < 0 && pence > 0) || (pounds > 0 && pence < 0)) {
			throw new IllegalArgumentException("Bad call of SterlingAmount constructor: " + pounds + ", " + pence + ". Pounds and pence sign must match.");
		}
		
		// Simply store as whole pence - makes calcs easy and makes representing a -ve number easy
		this.pence = 100 * pounds + pence;
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
	
	@Override
	public String toString() {
		return "SterlingAmount [pence=" + pence + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pence);
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
		
		long newPence = this.pence + otherSterlingAmount.pence;
		return new SterlingAmount(0, newPence);
	}
	
	/**
	 * @see cfs.supermarketpricing.money.MonetaryAmount#minus(cfs.supermarketpricing.money.MonetaryAmount)
	 */
	@Override
	public MonetaryAmount minus(MonetaryAmount amount) {
		// For the moment we assume only SterlingAmounts can be subtracted together
		// cfstodo: Check for a better way to declare this method
		if (!(amount instanceof SterlingAmount)) {
			throw new IllegalArgumentException("Only a SterlingAmount can be subtracted from a SterlingAmount, but we were given: " + amount);
		}
		SterlingAmount otherSterlingAmount = (SterlingAmount) amount;
		
		long newPence = this.pence - otherSterlingAmount.pence;
		return new SterlingAmount(0, newPence);
	}
	
	/**
	 * toBigDecimal
	 * 
	 * Conv to BigDecimal
	 */
	BigDecimal toBigDecimal() {
		return BigDecimal.valueOf(pence).divide(ONE_HUNDRED);
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
