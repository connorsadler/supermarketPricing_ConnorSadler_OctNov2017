package cfs.supermarketpricing.basket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import cfs.supermarketpricing.money.MoneySystem;
import cfs.supermarketpricing.money.SterlingAmount;
import cfs.supermarketpricing.money.SterlingMoneySystem;
import cfs.supermarketpricing.money.martiancredits.MartianCreditsAmount;
import cfs.supermarketpricing.money.martiancredits.MartianCreditsMoneySystem;
import cfs.supermarketpricing.sku.SimpleStockKeepingUnit;

/**
 * SimpleShoppingBasketTest
 * 
 * @author Connor
 */
public class SimpleShoppingBasketTest {
	/**
	 * testCorrectCurrency
	 * 
	 * Test using a basket with Sterling and items are Sterling
	 * Should add items OK
	 */
	@Test
	public void testCorrectCurrency() {
		MoneySystem<SterlingAmount> sterling = new SterlingMoneySystem();
		SimpleStockKeepingUnit beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", sterling.createFromString("0.50"));
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		assertThat("Check item count", shoppingBasket.getItems().size(), is(1));
	}
	
	/**
	 * testWrongCurrency
	 * 
	 * Test using a basket with Sterling and items are NOT Sterling
	 * Should fail with an expected exception
	 */
	@Test
	public void testWrongCurrency() {
		try {
			MoneySystem<SterlingAmount> sterling = new SterlingMoneySystem();
			MoneySystem<MartianCreditsAmount> martianCredits = new MartianCreditsMoneySystem();
			
			SimpleStockKeepingUnit beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", sterling.createFromString("0.50"));
			ShoppingBasket shoppingBasket = new SimpleShoppingBasket(martianCredits);
			shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
			fail("Test should fail but didn't");
		} catch (Exception e) {
			//e.printStackTrace();
			assertThat("Check for expected exception", 
					e.getMessage(), 
					is("Mismatching money system: Basket: MartianCreditsMoneySystem vs Item SKU Price: SterlingAmount [pounds=0, pence=50]"));
		}
	}
}
