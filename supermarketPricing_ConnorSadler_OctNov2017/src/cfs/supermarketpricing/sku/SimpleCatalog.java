package cfs.supermarketpricing.sku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleCatalog
 * 
 * In-memory list of SKUs
 */
public class SimpleCatalog implements Catalog {

	private final List<StockKeepingUnit> list = new ArrayList<>();

	@Override
	public List<StockKeepingUnit> getStockKeepingUnits() {
		return Collections.unmodifiableList(list);
	}
	
	public void add(StockKeepingUnit sku) {
		list.add(sku);
	}

}
