package cfs.supermarketpricing.discounts;

import cfs.supermarketpricing.basket.ShoppingBasket;

/**
 * DiscountsCalculator
 * 
 * Calculates the discounts which are applicable to a ShoppingBasket
 * 
 * @author Connor
 */
public interface DiscountsCalculator {

	/**
	 * executeDiscountsCalculation
	 * 
	 * Calculates the discounts which are applicable to a ShoppingBasket
	 */
	DiscountsCalculationResult executeDiscountsCalculation(ShoppingBasket shoppingBasket);

}
