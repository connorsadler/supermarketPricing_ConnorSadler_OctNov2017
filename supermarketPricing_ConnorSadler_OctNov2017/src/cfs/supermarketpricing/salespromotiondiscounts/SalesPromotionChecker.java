package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.List;
import java.util.stream.Collectors;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SalesPromotionChecker
 * 
 * Works to figure out 
 * 
 * @author Connor
 */
public interface SalesPromotionChecker {

	/**
	 * getItemsForStockKeepingUnit
	 * 
	 * Returns only the items from the basket with the specified StockKeepingUnit
	 */
	public default List<ShoppingBasketItem> getItemsForStockKeepingUnit(ShoppingBasket shoppingBasket, StockKeepingUnit stockKeepingUnit) {
		// Filter basket to get all items of the promotion's SKU
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
	List<Discount> executeCheck(ShoppingBasket shoppingBasket);

}
