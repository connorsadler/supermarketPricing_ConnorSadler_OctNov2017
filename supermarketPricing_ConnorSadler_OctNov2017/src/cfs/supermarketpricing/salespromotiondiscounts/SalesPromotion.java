package cfs.supermarketpricing.salespromotiondiscounts;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SalesPromotion
 * aka "Offer"
 * 
 * A type of discount/offer which may apply to a ShoppingBasket
 * e.g. 3 for 2 on SKU XXX
 *      2 for XXX price on SKU YYY
 * 
 * @author Connor
 */
public interface SalesPromotion {

	/**
	 * getStockKeepingUnit
	 * 
	 * The StockKeepingUnit to which this promotion applies
	 */
	StockKeepingUnit getStockKeepingUnit();

	/**
	 * getSalesPromotionChecker
	 */
	SalesPromotionChecker getSalesPromotionChecker();

}
