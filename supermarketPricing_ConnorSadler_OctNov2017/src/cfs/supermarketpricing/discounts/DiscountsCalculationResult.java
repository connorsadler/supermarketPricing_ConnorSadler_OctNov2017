package cfs.supermarketpricing.discounts;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * DiscountsCalculationResult
 * 
 * @author Connor
 */
public interface DiscountsCalculationResult {

	/**
	 * getDiscountTotal
	 * 
	 * This will be a negative figure if the customer is saving money, which is usually the case
	 */
	MonetaryAmount getDiscountTotal();

}
