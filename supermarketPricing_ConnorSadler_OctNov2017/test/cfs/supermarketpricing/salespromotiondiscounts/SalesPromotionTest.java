package cfs.supermarketpricing.salespromotiondiscounts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItem;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItemWithWeight;
import cfs.supermarketpricing.discounts.DiscountsCalculationResult;
import cfs.supermarketpricing.discounts.DiscountsCalculator;
import cfs.supermarketpricing.money.MoneySystem;
import cfs.supermarketpricing.money.SterlingAmount;
import cfs.supermarketpricing.money.SterlingMoneySystem;
import cfs.supermarketpricing.salespromotiondiscounts.SimpleDiscountsCalculator;
import cfs.supermarketpricing.salespromotiondiscounts.SimpleSalesPromotionRepository;
import cfs.supermarketpricing.salespromotiondiscounts.XForFixedAmountSalesPromotion;
import cfs.supermarketpricing.salespromotiondiscounts.XForYSalesPromotion;
import cfs.supermarketpricing.sku.SimpleStockKeepingUnit;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SalesPromotionTest
 * 
 * cfstodo: Needs some work on the comments here
 * 
 * @author Connor
 */
public class SalesPromotionTest {
	// Fixtures
	// We're going to work in Sterling for this test
	private final MoneySystem<SterlingAmount> sterling = new SterlingMoneySystem();
	// Our types of item
	private StockKeepingUnit beans300gTin;
	private SimpleStockKeepingUnit cokeCan;
	// SalesPromotions
	private SimpleSalesPromotionRepository salesPromotionRepository1_3for2beans;
	private SimpleSalesPromotionRepository salesPromotionRepository2_2for1beans;

	@Before
	public void setUp() {
		// Initial Setup
		beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", sterling.createFromString("0.50"));
		cokeCan = new SimpleStockKeepingUnit("Coke Can", sterling.createFromString("0.70"));
		
		// Setup available sales promotions
		salesPromotionRepository1_3for2beans = new SimpleSalesPromotionRepository();
		salesPromotionRepository1_3for2beans.addSalesPromotion(new XForYSalesPromotion(beans300gTin, 3, 2));
		salesPromotionRepository1_3for2beans.addSalesPromotion(new XForFixedAmountSalesPromotion(cokeCan, 2, sterling.createFromString("1")));
		salesPromotionRepository2_2for1beans = new SimpleSalesPromotionRepository();
		salesPromotionRepository2_2for1beans.addSalesPromotion(new XForYSalesPromotion(beans300gTin, 2, 1));
	}

	/**
	 * test1
	 * 
	 * cfstodo: Rename
	 */
	@Test
	public void test1() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SimpleDiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository1_3for2beans);
		DiscountsCalculationResult discountsResult = discountsCalculator.executeDiscountsCalculation(shoppingBasket);
		
		// Assertions
		assertThat(discountsResult.getDiscountTotal(), is(sterling.createFromString("-0.50")));
	}

	/**
	 * test2
	 * 
	 * cfstodo: Rename
	 */
	@Test
	public void test2() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
//		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SimpleDiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository1_3for2beans);
		DiscountsCalculationResult discountsResult = discountsCalculator.executeDiscountsCalculation(shoppingBasket);
		
		// Assertions
		assertThat(discountsResult.getDiscountTotal(), is(sterling.createFromString("0")));
	}
	
	/**
	 * test3
	 * 
	 * cfstodo: Rename
	 */
	@Test
	public void test3() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SimpleDiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository1_3for2beans);
		DiscountsCalculationResult discountsResult = discountsCalculator.executeDiscountsCalculation(shoppingBasket);
		
		// Assertions
		assertThat(discountsResult.getDiscountTotal(), is(sterling.createFromString("-1.50")));
		assertThat(discountsResult.getAllDiscountsApplied().size(), is(3));
	}
	
	/**
	 * test4
	 * 
	 * cfstodo: Rename
	 */
	@Test
	public void test4() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		
		// Run test
		SimpleDiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository2_2for1beans);
		DiscountsCalculationResult discountsResult = discountsCalculator.executeDiscountsCalculation(shoppingBasket);
		
		// Assertions
		assertThat(discountsResult.getDiscountTotal(), is(sterling.createFromString("-2.00")));
		assertThat(discountsResult.getAllDiscountsApplied().size(), is(4));
	}
}
