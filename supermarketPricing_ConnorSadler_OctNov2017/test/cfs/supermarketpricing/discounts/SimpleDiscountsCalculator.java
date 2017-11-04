package cfs.supermarketpricing.discounts;

import java.math.BigDecimal;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * SimpleDiscountsCalculator
 * 
 * @author Connor
 */
public class SimpleDiscountsCalculator implements DiscountsCalculator {

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculator#executeDiscountsCalculation(cfs.supermarketpricing.basket.ShoppingBasket)
	 */
	@Override
	public DiscountsCalculationResult executeDiscountsCalculation(ShoppingBasket shoppingBasket) {
		
		// cfstodo: Iterate through list of Discounts
		//          Check which ones apply
		//          Total the amounts up
		//          NOTE: The result should be negative
		
		MoneySystem<? extends MonetaryAmount> moneySystem = shoppingBasket.getMoneySystem();
		MonetaryAmount discountTotal = moneySystem.createFromBigDecimal(new BigDecimal("-0.89"));
		return new SimpleDiscountsCalculationResult(discountTotal);
	}

}
