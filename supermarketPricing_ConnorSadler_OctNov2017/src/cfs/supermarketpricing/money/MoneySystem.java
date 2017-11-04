package cfs.supermarketpricing.money;

import java.math.BigDecimal;

/**
 * MoneySystem
 * 
 * A system of money e.g. Sterling
 * Maybe I could rename this to Currency? Consider this later
 * 
 * @author Connor
 */
public interface MoneySystem<MA extends MonetaryAmount> {

	/**
	 * getDescription
	 */
	public String getDescription();
	/**
	 * createFromBigDecimal
	 * 
	 * Should return an instance of our MonetaryAmount type which has the equivalent value to this BigDecimal
	 */
	public MA createFromBigDecimal(BigDecimal bd);
	
	/**
	 * createFromBigDecimal
	 * 
	 * Should return an instance of our MonetaryAmount type which has the equivalent value to this String.
	 * This is just a pass-through to the BigDecimal version
	 */
	public default MA createFromString(String str) {
		return createFromBigDecimal(new BigDecimal(str));
	}

	/**
	 * isMatchingCurrency
	 * 
	 * Does the specified amount use this money system?
	 */
	public boolean isMatchingCurrency(MonetaryAmount amount);

}	
