package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.ArrayList;
import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * XForYSalesPromotionChecker
 * 
 * Checker for XForYSalesPromotion
 * 
 * @author Connor
 */
public class XForYSalesPromotionChecker implements SalesPromotionChecker {

	private XForYSalesPromotion xForYSalesPromotion;

	/**
	 * Constructor
	 */
	public XForYSalesPromotionChecker(XForYSalesPromotion xForYSalesPromotion) {
		this.xForYSalesPromotion = xForYSalesPromotion;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotionChecker#executeCheck(cfs.supermarketpricing.basket.ShoppingBasket)
	 */
	@Override
	public List<Discount> executeCheck(ShoppingBasket shoppingBasket) {
		List<Discount> result = new ArrayList<>();
		StockKeepingUnit stockKeepingUnit = xForYSalesPromotion.getStockKeepingUnit();
		List<ShoppingBasketItem> basketItemsForSalesPromotionSKU = getItemsForStockKeepingUnit(shoppingBasket, stockKeepingUnit);
		if (basketItemsForSalesPromotionSKU.size() > 0) {
			// Calc number of times we can apply this sales promotion
			int numberOfGroupsOfXItems = basketItemsForSalesPromotionSKU.size() / xForYSalesPromotion.getX();
			if (numberOfGroupsOfXItems > 0) {
				// Calc amount for each discount
				// ==  Usual amount for Y items - Usual amount for X items
				//     (This will be a negative number as it's a discount)
				MonetaryAmount usualPriceForYItems = stockKeepingUnit.getPrice().times(xForYSalesPromotion.getY());
				MonetaryAmount usualPriceForXItems = stockKeepingUnit.getPrice().times(xForYSalesPromotion.getX());
				MonetaryAmount discountAmount = usualPriceForYItems.minus(usualPriceForXItems);
				// Add all discounts
				for (int i=0; i < numberOfGroupsOfXItems; i++) {
					result.add(new SimpleDiscount(xForYSalesPromotion, discountAmount));
				}
			}
		}
		return result;
	}

}
