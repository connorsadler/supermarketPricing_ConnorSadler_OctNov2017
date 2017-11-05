package cfs.supermarketpricing.salespromotiondiscounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * SimpleDiscountList
 * 
 * In-memory list of Discount items
 * 
 * @author Connor
 */
public class SimpleDiscountList implements DiscountList {
	private final MoneySystem<? extends MonetaryAmount> moneySystem;
	private final List<Discount> discounts = new ArrayList<>();

	/**
	 * Constructor
	 */
	public SimpleDiscountList(MoneySystem<? extends MonetaryAmount> moneySystem) {
		this.moneySystem = moneySystem;
	}

	/**
	 * Constructor
	 */
	public SimpleDiscountList(MoneySystem<? extends MonetaryAmount> moneySystem, List<Discount> l) {
		this.moneySystem = moneySystem;
		this.discounts.addAll(l);
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.DiscountList#getTotalDiscount()
	 */
	public MonetaryAmount getTotalDiscount() {
		MonetaryAmount discountTotal = moneySystem.createFromBigDecimal(new BigDecimal("0"));
		for (Discount discount : discounts) {
			discountTotal = discountTotal.plus(discount.getDiscountAmount());
		}
		return discountTotal;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.DiscountList#getDiscounts()
	 */
	@Override
	public List<Discount> getDiscounts() {
		return Collections.unmodifiableList(discounts);
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.DiscountList#addAll(cfs.supermarketpricing.salespromotiondiscounts.DiscountList)
	 */
	@Override
	public void addAll(DiscountList l) {
		discounts.addAll(l.getDiscounts());
	}
	
	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.DiscountList#getSize()
	 */
	@Override
	public int getSize() {
		return discounts.size();
	}
}
