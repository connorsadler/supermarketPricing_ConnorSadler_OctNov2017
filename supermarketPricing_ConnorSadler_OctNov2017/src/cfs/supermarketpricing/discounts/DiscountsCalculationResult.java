package cfs.supermarketpricing.discounts;

import java.util.List;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.salespromotiondiscounts.Discount;

/**
 * DiscountsCalculationResult
 * 
 * @author Connor
 */
public interface DiscountsCalculationResult {
	
	/**
	 * getAllDiscountsApplied
	 * 
	 * Should return an immutable List
	 */
	public List<Discount> getAllDiscountsApplied();

	/**
	 * getDiscountTotal
	 * 
	 * This will be a negative figure if the customer is saving money, which is usually the case
	 * cfstodo: Change this?
	 */
	MonetaryAmount getDiscountTotal();

}
