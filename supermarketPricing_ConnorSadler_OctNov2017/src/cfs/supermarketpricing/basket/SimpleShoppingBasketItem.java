package cfs.supermarketpricing.basket;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SimpleShoppingBasketItem
 * 
 * @author Connor
 */
public class SimpleShoppingBasketItem implements ShoppingBasketItem {
	
	private final StockKeepingUnit stockKeepingUnit;
	
	public SimpleShoppingBasketItem(StockKeepingUnit stockKeepingUnit) {
		this.stockKeepingUnit = stockKeepingUnit;
	}

	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasketItem#getStockKeepingUnit()
	 */
	@Override
	public StockKeepingUnit getStockKeepingUnit() {
		return stockKeepingUnit;
	}

}
