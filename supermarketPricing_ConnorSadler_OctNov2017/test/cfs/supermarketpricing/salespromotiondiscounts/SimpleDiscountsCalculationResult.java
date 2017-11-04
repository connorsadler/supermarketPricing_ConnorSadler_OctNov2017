package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.Collections;
import java.util.List;

import cfs.supermarketpricing.discounts.DiscountsCalculationResult;
import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * SimpleDiscountsCalculationResult
 * 
 * @author Connor
 */
public class SimpleDiscountsCalculationResult implements DiscountsCalculationResult {

	// All discounts applied
	private List<Discount> allDiscountsApplied;
	// This will be a negative figure if the customer is saving money, which is usually the case
	private MonetaryAmount discountTotal;

	/**
	 * Constructor
	 */
	public SimpleDiscountsCalculationResult(List<Discount> allDiscountsApplied, MonetaryAmount discountTotal) {
		this.allDiscountsApplied = allDiscountsApplied;
		this.discountTotal = discountTotal;
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculationResult#getDiscountTotal()
	 */
	@Override
	public MonetaryAmount getDiscountTotal() {
		return discountTotal;
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculationResult#getAllDiscountsApplied()
	 */
	public List<Discount> getAllDiscountsApplied() {
		return Collections.unmodifiableList(allDiscountsApplied);
	}
}
