package cfs.supermarketpricing.basket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * SimpleShoppingBasket
 * 
 * Simple in-memory ShoppingBasket
 * 
 * @author Connor
 */
public class SimpleShoppingBasket implements ShoppingBasket {
	
	private final MoneySystem<? extends MonetaryAmount> moneySystem;
	private final List<ShoppingBasketItem> items = new ArrayList<>();

	/**
	 * Constructor
	 */
	public SimpleShoppingBasket(MoneySystem<? extends MonetaryAmount> moneySystem) {
		this.moneySystem = moneySystem;
	}

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
		// Check currency matches and reject if not
		if (!moneySystem.isMatchingCurrency(item.getStockKeepingUnit().getPrice())) {
			throw new RuntimeException(String.format("Mismatching money system: Basket: %1$s vs Item SKU Price: %2$s", 
										             moneySystem.getDescription(), 
										             item.getStockKeepingUnit().getPrice()));
		}
		
		// Item OK
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

	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasket#getMoneySystem()
	 */
	@Override
	public MoneySystem<? extends MonetaryAmount> getMoneySystem() {
		return moneySystem;
	}
}
