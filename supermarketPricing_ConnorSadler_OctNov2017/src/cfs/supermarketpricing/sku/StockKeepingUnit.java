package cfs.supermarketpricing.sku;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * StockKeepingUnit aka SKU
 * 
 * Identifies a type of product for sale
 * e.g. Heinz baked beans 300g tin
 * Says nothing about how many of such an item we have in stock
 * An instance of this class will relate to many physical items in stock
 * 
 * Shamelessly taken from Google:
 *   A stock keeping unit (SKU) is a product and service identification code for a store or product, often 
 *   portrayed as a machine-readable bar code that helps track the item for inventory. 
 *   A stock keeping unit (SKU) does not need to be assigned to physical products in inventory.
 */
public interface StockKeepingUnit {
	
	/**
	 * getDescription
	 * 
	 * User-friendly description e.g. Beans 300g tin
	 */
	public String getDescription();
	
	/**
	 * getPrice
	 * 
	 * Price for a single item of this SKU
	 */
	public MonetaryAmount getPrice();
}
