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
 * XForFixedAmountSalesPromotionTest
 * 
 * Test XForFixedAmountSalesPromotion and it's checker
 * 
 * @author Connor
 */
public class XForFixedAmountSalesPromotionTest {
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
	 * addCoke
	 * 
	 * Add some coke to a basket
	 */
	private void addCoke(ShoppingBasket shoppingBasket, int numberOfBeans) {
		for (int i=0; i < numberOfBeans; i++) {
			shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		}		
	}

	/**
	 * test_3ForAPound_doesNotApply
	 * 
	 * We haven't bought enough coke to trigger the promotion
	 */
	@Test
	public void test_3ForAPound_doesNotApply() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 2);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(beans300gTin, 3, sterling.createFromString("1.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(0));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("0")));
	}
	
	/**
	 * test_3ForAPound_appliesOnce
	 * 
	 * We just bought enough coke to trigger the promotion
	 */
	@Test
	public void test_3ForAPound_appliesOnce() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 3);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 3, sterling.createFromString("1.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-1.10")));
	}
	
	/**
	 * test_3ForAPound_appliesOnce_basketOrderNotImportant
	 * 
	 * We just bought enough coke to trigger the promotion
	 * There are beans inbetween the coke in the basket which should not affect the promotion being triggered
	 */
	@Test
	public void test_3ForAPound_appliesOnce_basketOrderNotImportant() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));		
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 3, sterling.createFromString("1.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-1.10")));
	}
	
	/**
	 * test_3ForAPound_appliesThreeTimes
	 * 
	 * Test "3 for £1" applies three times
	 */
	@Test
	public void test_3ForAPound_appliesThreeTimes() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 9);
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 3, sterling.createFromString("1.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(3));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-3.30")));
	}
	
	/**
	 * test_4ForTwoPounds_doesNotApply
	 * 
	 * We haven't bought enough beans to trigger the promotion
	 */
	@Test
	public void test_4ForTwoPounds_doesNotApply() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 3);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 4, sterling.createFromString("2.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(0));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("0")));
	}
	
	/**
	 * test_4ForTwoPounds_appliesOnce
	 * 
	 * We just bought enough beans to trigger the promotion
	 */
	@Test
	public void test_4ForTwoPounds_appliesOnce() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 4);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 4, sterling.createFromString("2.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(1));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-0.80")));
	}
	
	/**
	 * test_4ForTwoPounds_appliesTwice
	 * 
	 * We just bought enough beans to trigger the promotion twice
	 */
	@Test
	public void test_4ForTwoPounds_appliesTwice() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		addCoke(shoppingBasket, 8);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		
		// Run test
		SalesPromotion salesPromotion = new XForFixedAmountSalesPromotion(cokeCan, 4, sterling.createFromString("2.00"));
		XForFixedAmountSalesPromotionChecker checker = (XForFixedAmountSalesPromotionChecker) salesPromotion.getSalesPromotionChecker();
		DiscountList result = checker.executeCheck(shoppingBasket);
		
		// Assertions
		assertThat(result.getSize(), is(2));
		assertThat(result.getTotalDiscount(), is(sterling.createFromString("-1.60")));
	}
}
