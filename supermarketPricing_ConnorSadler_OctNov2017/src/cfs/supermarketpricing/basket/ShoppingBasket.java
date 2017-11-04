package cfs.supermarketpricing.basket;

import java.util.List;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * ShoppingBasket
 * 
 * A list of items which a shopper wishes to buy
 * 
 * @author Connor
 */
public interface ShoppingBasket {

	/**
	 * getMoneySystem
	 * 
	 * The MoneySystem this basket uses
	 * If items in a different currency are added, currently an exception is thrown
	 * This avoids any currency conversion - this could be changed in future to allow this
	 */
	public MoneySystem<? extends MonetaryAmount> getMoneySystem();

	/**
	 * getItems
	 * 
	 * Should return an immutable Collection
	 */
	public List<ShoppingBasketItem> getItems();
	
	/**
	 * addItem
	 * 
	 * Adds a item to the basket
	 * Must check MoneySystem used by the item
	 */
	public void addItem(ShoppingBasketItem item);
	
	/**
	 * removeItem
	 * 
	 * cfstodo: For future enhancement, don't implement this yet
	 */
	public void removeItem(ShoppingBasketItem item);
}
