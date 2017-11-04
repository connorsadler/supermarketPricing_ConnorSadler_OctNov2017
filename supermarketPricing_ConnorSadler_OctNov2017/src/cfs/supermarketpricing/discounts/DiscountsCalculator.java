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
	 * We need to work out from the basket which offers are applicable, then apply them
	 * The applied offers need to be stored in the result
	 */
	DiscountsCalculationResult executeDiscountsCalculation(ShoppingBasket shoppingBasket);

}
