package cfs.supermarketpricing.discounts;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * SimpleDiscountsCalculationResult
 * 
 * @author Connor
 */
public class SimpleDiscountsCalculationResult implements DiscountsCalculationResult {

	private MonetaryAmount discountTotal;

	/**
	 * Constructor
	 */
	public SimpleDiscountsCalculationResult(MonetaryAmount discountTotal) {
		this.discountTotal = discountTotal;
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculationResult#getDiscountTotal()
	 */
	@Override
	public MonetaryAmount getDiscountTotal() {
		return discountTotal;
	}

}
