package cfs.supermarketpricing.basket;

import java.util.List;

/**
 * ShoppingBasket
 * 
 * A list of items which a shopper wishes to buy
 * 
 * @author Connor
 */
public interface ShoppingBasket {

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
	 */
	public void addItem(ShoppingBasketItem item);
	
	/**
	 * removeItem
	 * 
	 * cfstodo: For future enhancement, don't implement this yet
	 */
	public void removeItem(ShoppingBasketItem item);
}
