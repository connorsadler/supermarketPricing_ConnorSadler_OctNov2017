package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.List;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * DiscountList
 * 
 * List of Discount items
 * 
 * @author Connor
 */
public interface DiscountList {

	/**
	 * getTotalDiscount
	 * 
	 * Total the amounts up
	 * NOTE: The result should be negative
	 */
	public MonetaryAmount getTotalDiscount();

	/**
	 * getDiscounts
	 * 
	 * Should return an immutable list
	 */
	public List<Discount> getDiscounts();

	/**
	 * addAll
	 */
	public void addAll(DiscountList l);

	/**
	 * getSize
	 * 
	 * Size of list
	 */
	public int getSize();
}
