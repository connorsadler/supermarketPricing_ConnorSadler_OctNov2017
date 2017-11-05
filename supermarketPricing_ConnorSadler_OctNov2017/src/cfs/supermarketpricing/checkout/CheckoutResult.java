package cfs.supermarketpricing.checkout;

import cfs.supermarketpricing.discounts.DiscountsCalculationResult;
import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * CheckoutResult
 * 
 * @author Connor
 */
public class CheckoutResult {

	private final MonetaryAmount subtotal;
	// Final total after discounts applied
	private final MonetaryAmount totalToPay;
	// Any discounts applied
	private DiscountsCalculationResult discountsCalculationResult;

	/**
	 * Constructor
	 */
	public CheckoutResult(MonetaryAmount subtotal, MonetaryAmount totalToPay, DiscountsCalculationResult discountsCalculationResult) {
		this.subtotal = subtotal;
		this.totalToPay = totalToPay;
		this.discountsCalculationResult = discountsCalculationResult;
	}

	/**
	 * getSubTotal
	 */
	public MonetaryAmount getSubTotal() {
		return subtotal;
	}

	/**
	 * getTotalToPay
	 */
	public MonetaryAmount getTotalToPay() {
		return totalToPay;
	}
	
	/**
	 * getDiscountsCalculationResult
	 */
	public DiscountsCalculationResult getDiscountsCalculationResult() {
		return discountsCalculationResult;
	}

}
