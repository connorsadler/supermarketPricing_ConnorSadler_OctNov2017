package cfs.supermarketpricing.sku;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * SimpleStockKeepingUnit
 * 
 * Simple impl of StockKeepingUnit
 * 
 * @author Connor
 */
public class SimpleStockKeepingUnit implements StockKeepingUnit {
	
	private String description;
	private MonetaryAmount price;

	public SimpleStockKeepingUnit(String description, MonetaryAmount price) {
		this.description = description;
		this.price = price;
	}

	/**
	 * @see cfs.supermarketpricing.sku.StockKeepingUnit#getPrice()
	 */
	@Override
	public MonetaryAmount getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "SimpleStockKeepingUnit [description=" + description + ", price=" + price + "]";
	}

	
}
