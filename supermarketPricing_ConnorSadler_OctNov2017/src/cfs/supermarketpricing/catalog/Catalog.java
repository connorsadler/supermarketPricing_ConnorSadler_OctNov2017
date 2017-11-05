package cfs.supermarketpricing.catalog;

import java.util.List;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * Catalog
 * 
 * Repository of available SKUs
 * e.g. you might have one of these for branch of a supermarket to see what items it sells 
 */
public interface Catalog {

	/**
	 * getStockKeepingUnits
	 * 
	 * Should return an immutable list of SKUs
	 */
	public List<StockKeepingUnit> getStockKeepingUnits();
	
}
