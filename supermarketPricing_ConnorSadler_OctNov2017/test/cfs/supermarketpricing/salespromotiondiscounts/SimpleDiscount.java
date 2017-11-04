package cfs.supermarketpricing.salespromotiondiscounts;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * SimpleDiscount
 * 
 * Simple implementation of Discount
 * 
 * @author Connor
 */
public class SimpleDiscount implements Discount {
	
	private SalesPromotion salesPromotion;
	private MonetaryAmount discountAmount;

	public SimpleDiscount(SalesPromotion salesPromotion, MonetaryAmount discountAmount) {
		this.salesPromotion = salesPromotion;
		this.discountAmount = discountAmount;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.Discount#getSalesPromotion()
	 */
	@Override
	public SalesPromotion getSalesPromotion() {
		return salesPromotion;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.Discount#getDiscountAmount()
	 */
	@Override
	public MonetaryAmount getDiscountAmount() {
		return discountAmount;
	}

	@Override
	public String toString() {
		return "SimpleDiscount [salesPromotion=" + salesPromotion + ", discountAmount=" + discountAmount + "]";
	}

	
}
