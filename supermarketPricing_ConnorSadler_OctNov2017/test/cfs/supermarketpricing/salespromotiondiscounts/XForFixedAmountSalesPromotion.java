package cfs.supermarketpricing.salespromotiondiscounts;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * XForFixedAmountSalesPromotion
 * 
 * e.g. 2 for £1 on Coke
 * 
 * @author Connor
 */
public class XForFixedAmountSalesPromotion implements SalesPromotion {
	
	private final StockKeepingUnit stockKeepingUnit;
	// The number a customer has to buy to trigger the offer
	// e.g. The "2" in: 2 for £1 on Coke
	private final int x;
	// The price they pay for "x" items - usually/hopefully lower than "x" multiplied by the SKU price
	// e.g. The "£1" in: 2 for £1 on Coke
	private final MonetaryAmount offerPrice;

	/**
	 * Constructor
	 */
	public XForFixedAmountSalesPromotion(StockKeepingUnit stockKeepingUnit, int x, MonetaryAmount offerPrice) {
		this.stockKeepingUnit = stockKeepingUnit;
		this.x = x;
		this.offerPrice = offerPrice;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotion#getStockKeepingUnit()
	 */
	@Override
	public StockKeepingUnit getStockKeepingUnit() {
		return stockKeepingUnit;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotion#getSalesPromotionChecker()
	 */
	@Override
	public SalesPromotionChecker getSalesPromotionChecker() {
		return new XForFixedAmountSalesPromotionChecker(this);
	}

	public int getX() {
		return x;
	}

	public MonetaryAmount getOfferPrice() {
		return offerPrice;
	}

	@Override
	public String toString() {
		return "XForFixedAmountSalesPromotion [stockKeepingUnit=" + stockKeepingUnit + ", x=" + x + ", offerPrice="
				+ offerPrice + "]";
	}
	
	
	
}
