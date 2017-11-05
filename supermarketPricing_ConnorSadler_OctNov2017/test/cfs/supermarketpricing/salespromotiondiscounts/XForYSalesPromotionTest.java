package cfs.supermarketpricing.salespromotiondiscounts;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


import org.junit.Before;
import org.junit.Test;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItem;
import cfs.supermarketpricing.money.MoneySystem;
import cfs.supermarketpricing.money.SterlingAmount;
import cfs.supermarketpricing.money.SterlingMoneySystem;
import cfs.supermarketpricing.sku.SimpleStockKeepingUnit;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * XForYSalesPromotionTest
 * 
 * Test XForYSalesPromotion and it's checker
 * 
 * @author Connor
 */
public class XForYSalesPromotionTest {
	// Fixtures
	// We're going to work in Sterling for this test
	private final MoneySystem<SterlingAmount> sterling = new SterlingMoneySystem();
	// Our types of item
	private StockKeepingUnit beans300gTin;
	private SimpleStockKeepingUnit cokeCan;

	@Before
	public void setUp() {
		// Initial Setup
		beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", sterling.createFromString("0.50"));
		cokeCan = new SimpleStockKeepingUnit("Coke Can", sterling.createFromString("0.70"));
	}

	/**
	 * addBeans
	 * 
	 * Add some beans to a basket
	 */
	private void addBeans(ShoppingBasket shoppingBasket, int numberOfBeans) {
		for (int i=0; i < numberOfBeans; i++) {
			shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		}		
	}

	/**
	 * test_3For2_doesNotApply
	 * 
	 * We haven't bought enough beans to trigger the promotion
	 */
	@Test
	public void test_3For2_doesNotApply() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 2);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 3, 2);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(0));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("0")));
	}
	
	/**
	 * test_3For2_appliesOnce
	 * 
	 * We just bought enough beans to trigger the promotion
	 */
	@Test
	public void test_3For2_appliesOnce() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 3);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 3, 2);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-0.50")));
	}
	
	/**
	 * test_3For2_appliesOnce_basketOrderNotImportant
	 * 
	 * We just bought enough beans to trigger the promotion
	 * There are coke cans inbetween the beans in the basket which should not affect the promotion being triggered
	 */
	@Test
	public void test_3For2_appliesOnce_basketOrderNotImportant() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));		
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 3, 2);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-0.50")));
	}
	
	/**
	 * test_3For2_appliesThreeTimes
	 * 
	 * Test "3 for 2" applies three times
	 */
	@Test
	public void test_3For2_appliesThreeTimes() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 9);
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 3, 2);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(3));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-1.50")));
	}
	
	/**
	 * test_4For3_doesNotApply
	 * 
	 * We haven't bought enough beans to trigger the promotion
	 */
	@Test
	public void test_4For3_doesNotApply() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 3);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 4, 3);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(0));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("0")));
	}
	
	/**
	 * test_4For3_appliesOnce
	 * 
	 * We just bought enough beans to trigger the promotion
	 */
	@Test
	public void test_4For3_appliesOnce() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 4);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 4, 3);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-0.50")));
	}
	
	/**
	 * test_4For3_appliesTwice
	 * 
	 * We just bought enough beans to trigger the promotion twice
	 */
	@Test
	public void test_4For3_appliesTwice() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addBeans(shoppingBasket, 8);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForYSalesPromotion(beans300gTin, 4, 3);
		XForYSalesPromotionChecker checker = (XForYSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(2));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-1.00")));
	}
	
	/**
	 * testMultipleSalesPromotionsForSameItem
	 * 
	 * We have a "3 for 2" and a "2 for 1" running at the same time
	 * The checker deals with a single SalesPromotion so this test is in CheckoutTest.testCheckout2_multipleSalesPromotionsForSameItem
	 */
	@Test
	public void testMultipleSalesPromotionsForSameItem() {
	}
	
	
	
}
