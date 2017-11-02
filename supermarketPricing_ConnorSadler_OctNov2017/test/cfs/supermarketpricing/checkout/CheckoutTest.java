package cfs.supermarketpricing.checkout;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItem;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItemWithWeight;
import cfs.supermarketpricing.money.SterlingFactory;
import cfs.supermarketpricing.sku.SimpleStockKeepingUnit;
import cfs.supermarketpricing.sku.StockKeepingUnit;

public class CheckoutTest {
	// Fixtures
	private StockKeepingUnit beans300gTin;
	private StockKeepingUnit orangesPerKilo;

	@Before
	public void setUp() {
		// Initial Setup
		beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", SterlingFactory.createPoundsAmount(0, 50));
		// cfstodo: "Oranges per kilo" has a price per weight rather than a price per item like beans
		orangesPerKilo = new SimpleStockKeepingUnit("Oranges per kilo", SterlingFactory.createPoundsAmount(0, 20));
	}

	/**
	 * testCheckout1
	 */
	@Test
	public void testCheckout1() {
		
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket();
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItemWithWeight(orangesPerKilo, 0.2f));
		
		// Perform a checkout
		CheckoutResult result = new CheckoutCalculator(shoppingBasket).executeCheckout();
		
		// cfstodo: Assertions
		assertThat("Check sub total", result.getSubTotal(), is(SterlingFactory.createPoundsAmount(3, 30)));
		assertThat("Check total to pay", result.getTotalToPay(), is(SterlingFactory.createPoundsAmount(2, 40)));
	}

}
