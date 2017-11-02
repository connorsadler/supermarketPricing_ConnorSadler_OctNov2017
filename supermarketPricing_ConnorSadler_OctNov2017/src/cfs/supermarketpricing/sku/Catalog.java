package cfs.supermarketpricing.sku;

import java.util.List;

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
