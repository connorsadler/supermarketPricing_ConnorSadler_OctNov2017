package cfs.supermarketpricing.salespromotiondiscounts;

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
	private DiscountList allDiscountsApplied;

	/**
	 * Constructor
	 */
	public SimpleDiscountsCalculationResult(DiscountList allDiscountsApplied) {
		this.allDiscountsApplied = allDiscountsApplied;
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculationResult#getDiscountTotal()
	 */
	@Override
	public MonetaryAmount getDiscountTotal() {
		return allDiscountsApplied.getTotalDiscount();
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculationResult#getAllDiscountsApplied()
	 */
	public List<Discount> getAllDiscountsApplied() {
		return allDiscountsApplied.getDiscounts();
	}
}
