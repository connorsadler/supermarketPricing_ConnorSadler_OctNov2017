package cfs.supermarketpricing.checkout;

import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.SterlingFactory;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * CheckoutCalculator
 * 
 * cfstodo: Comments
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
		
		// cfstodo: This class shouldn't work only for "Sterling", it should work for any currency
		//          Need to inject some sort of "monetary system" somewhere
		MonetaryAmount subtotal = SterlingFactory.createPoundsAmount(0, 0);
		
		// Calculate the sub total by adding up all items
		List<ShoppingBasketItem> items = shoppingBasket.getItems();
		for (ShoppingBasketItem shoppingBasketItem : items) {
			MonetaryAmount lineAmount = calcSingleItemAmount(shoppingBasketItem);
			subtotal = subtotal.plus(lineAmount);
		}
				
		// cfstodo: Apply any offers
		//          We need to work out from the basket which offers are applicable, then apply them
		//          The applied offers need to be stored in the result
		
		// cfstodo: Create result
		CheckoutResult result = new CheckoutResult(subtotal);
		return result;
	}

	/**
	 * calcLineAmount
	 */
	private MonetaryAmount calcSingleItemAmount(ShoppingBasketItem shoppingBasketItem) {
		
		// cfstodo: Do this properly for SKUs which require a weight!
		StockKeepingUnit sku = shoppingBasketItem.getStockKeepingUnit();
		return sku.getPrice();
	}

}
