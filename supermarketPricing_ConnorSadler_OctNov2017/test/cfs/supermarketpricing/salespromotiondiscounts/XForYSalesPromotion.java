package cfs.supermarketpricing.salespromotiondiscounts;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * XForYSalesPromotion
 * 
 * e.g. 3 for 2 on beans
 * 
 * @author Connor
 */
public class XForYSalesPromotion implements SalesPromotion {

	private final StockKeepingUnit stockKeepingUnit;
	// The higher number that a customer has to buy to get some of the items effectively for free
	// e.g. the "3" in a 3 for 2
	private final int x;
	// The lower number of items that a customer will actually pay for
	// e.g. the "2" in a 3 for 2
	private final int y;

	/**
	 * Constructor
	 */
	public XForYSalesPromotion(StockKeepingUnit stockKeepingUnit, int x, int y) {
		this.stockKeepingUnit = stockKeepingUnit;
		this.x = x;
		this.y = y;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotion#getStockKeepingUnit()
	 */
	@Override
	public StockKeepingUnit getStockKeepingUnit() {
		return stockKeepingUnit;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotion#getSalesPromotionChecker()
	 */
	@Override
	public SalesPromotionChecker getSalesPromotionChecker() {
		return new XForYSalesPromotionChecker(this);
	}

	@Override
	public String toString() {
		return "XForYSalesPromotion [stockKeepingUnit=" + stockKeepingUnit + ", x=" + x + ", y=" + y + "]";
	}
}
