package cfs.supermarketpricing.checkout;

import java.math.BigDecimal;
import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * CheckoutCalculator
 * 
 * Calculates a total price for a ShoppingBasket at the checkout
 * 
 * @author Connor
 */
public class CheckoutCalculator {
	private ShoppingBasket shoppingBasket;

	/**
	 * Constructor
	 */
	public CheckoutCalculator(ShoppingBasket shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

	/**
	 * executeCheckout
	 */
	public CheckoutResult executeCheckout() {
		// We use the same MoneySystem (currency) as the basket
		MoneySystem<? extends MonetaryAmount> moneySystem = shoppingBasket.getMoneySystem();

		// Start with a zero subtotal
		MonetaryAmount subtotal = moneySystem.createFromBigDecimal(new BigDecimal("0"));
		
		// Calculate the sub total by adding up all items
		List<ShoppingBasketItem> items = shoppingBasket.getItems();
		for (ShoppingBasketItem shoppingBasketItem : items) {
			MonetaryAmount lineAmount = shoppingBasketItem.calcAmount();
			subtotal = subtotal.plus(lineAmount);
		}
				
		// cfstodo: Apply any offers/discounts
		//          We need to work out from the basket which offers are applicable, then apply them
		//          The applied offers need to be stored in the result
		MonetaryAmount totalToPay = subtotal;
		
		// Create result
		CheckoutResult result = new CheckoutResult(subtotal, totalToPay);
		return result;
	}
}
