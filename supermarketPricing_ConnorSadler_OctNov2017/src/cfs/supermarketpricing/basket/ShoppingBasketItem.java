package cfs.supermarketpricing.basket;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * ShoppingBasketItem
 * 
 * Item in a basket
 * Relates to a SKU which gives a current price
 * 
 * I kept this simple so that if you want to buy multiple tins of beans you'll add more than one
 * item to your basket. But that could be changed in future if required
 * i.e. we could add a "numberOfItems" property to ShoppingBasketItem
 * I later added a subtype "ShoppingBasketItemWithWeight" which is used when you have something like fruit
 * which is priced per unit of weight.
 * 
 * @author Connor
 */
public interface ShoppingBasketItem {
	
	/**
	 * getStockKeepingUnit
	 * 
	 * Which SKU we're buying for this item
	 */
	public StockKeepingUnit getStockKeepingUnit();

	/**
	 * calcAmount
	 * 
	 * Calculate the amount to add to the final bill for this item
	 */
	public MonetaryAmount calcAmount();
}
