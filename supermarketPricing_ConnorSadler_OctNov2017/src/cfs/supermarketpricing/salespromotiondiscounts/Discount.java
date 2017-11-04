package cfs.supermarketpricing.salespromotiondiscounts;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * Discount
 * 
 * A single discount line triggered by the activation of a SalesPromotion 
 * 
 * @author Connor
 */
public interface Discount {
	
	SalesPromotion getSalesPromotion();

	/**
	 * getDiscountAmount
	 * 
	 * The amount of discount given by this single application of a SalesPromotion
	 * Should be a negative number
	 */
	MonetaryAmount getDiscountAmount();

}
