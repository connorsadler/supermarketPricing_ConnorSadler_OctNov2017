package cfs.supermarketpricing.checkout;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * CheckoutResult
 * 
 * @author Connor
 */
public class CheckoutResult {

	private MonetaryAmount subtotal;

	/**
	 * Constructor
	 */
	public CheckoutResult(MonetaryAmount subtotal) {
		this.subtotal = subtotal;
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
	public Object getTotalToPay() {
		// TODO Auto-generated method stub
		return null;
	}

}
