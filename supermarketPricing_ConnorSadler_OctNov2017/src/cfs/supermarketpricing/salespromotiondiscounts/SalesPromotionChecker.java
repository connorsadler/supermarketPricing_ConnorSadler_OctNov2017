package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.List;
import java.util.stream.Collectors;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SalesPromotionChecker
 * 
 * Works alongside a SalesPromotion to check if it's applicable for a given ShoppingBasket
 * I pulled this logic out of SalesPromotion itself to keep it cleaner
 * 
 * @author Connor
 */
public interface SalesPromotionChecker {

	/**
	 * getItemsForStockKeepingUnit
	 * 
	 * Convenience method for SalesPromotionCheckers which need access to
	 * only the items from the basket with their specific StockKeepingUnit
	 */
	public default List<ShoppingBasketItem> getItemsForStockKeepingUnit(ShoppingBasket shoppingBasket, StockKeepingUnit stockKeepingUnit) {
		return shoppingBasket.getItems()
			   .stream()
			   .filter(sbi -> sbi.getStockKeepingUnit().equals(stockKeepingUnit))
			   .collect(Collectors.toList());
	}

	
	/**
	 * executeCheck
	 * 
	 * Should return the list of Discounts (if any) that apply to the specified basket for the SalesPromotion we're checking
	 */
	DiscountList executeCheck(ShoppingBasket shoppingBasket);

}
