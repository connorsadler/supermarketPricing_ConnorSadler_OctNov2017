package cfs.supermarketpricing.checkout;

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

	/**
	 * Constructor
	 */
	public CheckoutResult(MonetaryAmount subtotal, MonetaryAmount totalToPay) {
		this.subtotal = subtotal;
		this.totalToPay = totalToPay;
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

}
