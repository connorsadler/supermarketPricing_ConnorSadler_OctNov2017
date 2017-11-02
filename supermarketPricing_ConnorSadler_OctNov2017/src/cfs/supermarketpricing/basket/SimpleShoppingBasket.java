package cfs.supermarketpricing.basket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleShoppingBasket
 * 
 * Simple in-memory ShoppingBasket
 * 
 * @author Connor
 */
public class SimpleShoppingBasket implements ShoppingBasket {
	
	private final List<ShoppingBasketItem> items = new ArrayList<>();

	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasket#getItems()
	 */
	@Override
	public List<ShoppingBasketItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasket#addItem(cfs.supermarketpricing.basket.ShoppingBasketItem)
	 */
	@Override
	public void addItem(ShoppingBasketItem item) {
		items.add(item);
	}
	
	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasket#removeItem(cfs.supermarketpricing.basket.ShoppingBasketItem)
	 */
	@Override
	public void removeItem(ShoppingBasketItem item) {
		// Not yet implemented - for future enhancement
		throw new UnsupportedOperationException("removeItem is not yet implemented - for future enhancement");
	}
}
