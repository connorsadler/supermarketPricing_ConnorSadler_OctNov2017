package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.ArrayList;
import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * XForFixedAmountSalesPromotionChecker
 * 
 * Checker for XForFixedAmountSalesPromotion
 * 
 * @author Connor
 */
public class XForFixedAmountSalesPromotionChecker implements SalesPromotionChecker {
	private XForFixedAmountSalesPromotion xForFixedAmountSalesPromotion;

	/**
	 * Constructor
	 */
	public XForFixedAmountSalesPromotionChecker(XForFixedAmountSalesPromotion xForFixedAmountSalesPromotion) {
		this.xForFixedAmountSalesPromotion = xForFixedAmountSalesPromotion;
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotionChecker#executeCheck(cfs.supermarketpricing.basket.ShoppingBasket)
	 */
	@Override
	public List<Discount> executeCheck(ShoppingBasket shoppingBasket) {
		List<Discount> result = new ArrayList<>();
		StockKeepingUnit stockKeepingUnit = xForFixedAmountSalesPromotion.getStockKeepingUnit();
		List<ShoppingBasketItem> basketItemsForSalesPromotionSKU = getItemsForStockKeepingUnit(shoppingBasket, stockKeepingUnit);
		if (basketItemsForSalesPromotionSKU.size() > 0) {
			// Calc number of times we can apply this sales promotion
			int numberOfGroupsOfXItems = basketItemsForSalesPromotionSKU.size() / xForFixedAmountSalesPromotion.getX();
			if (numberOfGroupsOfXItems > 0) {
				// Calc amount for each discount
				// ==  Fixed Amount - Usual amount for X items
				//     (This will be a negative number as it's a discount)
				MonetaryAmount usualPriceForXItems = stockKeepingUnit.getPrice().times(xForFixedAmountSalesPromotion.getX());
				MonetaryAmount discountAmount = xForFixedAmountSalesPromotion.getOfferPrice().minus(usualPriceForXItems);
				// Add all discounts
				for (int i=0; i < numberOfGroupsOfXItems; i++) {
					result.add(new SimpleDiscount(xForFixedAmountSalesPromotion, discountAmount));
				}
			}
		}
		return result;
	}

}
