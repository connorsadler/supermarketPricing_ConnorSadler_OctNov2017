package cfs.supermarketpricing.catalog;

import java.util.List;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * Repository of available SKUs
 */
public interface Catalog {

	/**
	 * getStockKeepingUnits
	 * 
	 * Should return an immutable list of SKUs
	 */
	public List<StockKeepingUnit> getStockKeepingUnits();
	
}
