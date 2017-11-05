package cfs.supermarketpricing.checkout;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.ShoppingBasketItem;
import cfs.supermarketpricing.basket.SimpleShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItem;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItemWithWeight;
import cfs.supermarketpricing.discounts.DiscountsCalculator;
import cfs.supermarketpricing.money.MoneySystem;
import cfs.supermarketpricing.money.SterlingAmount;
import cfs.supermarketpricing.money.SterlingMoneySystem;
import cfs.supermarketpricing.salespromotiondiscounts.Discount;
import cfs.supermarketpricing.salespromotiondiscounts.SimpleDiscountsCalculator;
import cfs.supermarketpricing.salespromotiondiscounts.SimpleSalesPromotionRepository;
import cfs.supermarketpricing.salespromotiondiscounts.XForFixedAmountSalesPromotion;
import cfs.supermarketpricing.salespromotiondiscounts.XForYSalesPromotion;
import cfs.supermarketpricing.sku.SimpleStockKeepingUnit;
import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * CheckoutCalculatorTest
 * 
 * Test for CheckoutCalculator
 * 
 * @author Connor
 */
public class CheckoutCalculatorTest {
	// Fixtures
	// We're going to work in Sterling for this test
	private final MoneySystem<SterlingAmount> sterling = new SterlingMoneySystem();
	// Our types of item
	private StockKeepingUnit beans300gTin;
	private SimpleStockKeepingUnit cokeCan;
	private StockKeepingUnit orangesPerKilo;

	@Before
	public void setUp() {
		// Initial Setup
		beans300gTin = new SimpleStockKeepingUnit("Beans 300g tin", sterling.createFromString("0.50"));
		cokeCan = new SimpleStockKeepingUnit("Coke Can", sterling.createFromString("0.70"));
		// Note: "Oranges per kilo" has a price per weight rather than a price per item like beans
		orangesPerKilo = new SimpleStockKeepingUnit("Oranges per kilo", sterling.createFromString("1.99"));
	}

	/**
	 * logResult
	 * 
	 * This is to aid the developer if problems happen
	 * We dump out the basket and CheckoutResult
	 */
	private void logResult(ShoppingBasket shoppingBasket, CheckoutResult result) {
		System.out.println(">>> logResult");

		System.out.println("---");
		System.out.println("Shopping basket:");
		for (ShoppingBasketItem item : shoppingBasket.getItems()) {
			System.out.println("  item: " + item);
		}
		System.out.println("---");
		System.out.println("Sub Total: " + result.getSubTotal());
		System.out.println("---");
		System.out.println("Discounts:");
		for (Discount discount : result.getDiscountsCalculationResult().getAllDiscountsApplied()) {
			System.out.println("  discount: " + discount);
		}
		System.out.println("Total Savings: " +  result.getDiscountsCalculationResult().getDiscountTotal());
		System.out.println("---");
		System.out.println("Total to Pay: " + result.getTotalToPay());
		System.out.println("---");
		
		System.out.println("<<< logResult");
	}
	
	/**
	 * testCheckout1_scenarioFromRequirementsDocument
	 * 
	 * Test the scenario from: Developer-Test - Supermarket-v2.txt
	 */
	@Test
	public void testCheckout1_scenarioFromRequirementsDocument() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		shoppingBasket.addItem(new SimpleShoppingBasketItem(cokeCan));
		shoppingBasket.addItem(new SimpleShoppingBasketItemWithWeight(orangesPerKilo, 0.2f));
		
		// Setup available sales promotions
		SimpleSalesPromotionRepository salesPromotionRepository = new SimpleSalesPromotionRepository();
		salesPromotionRepository.addSalesPromotion(new XForYSalesPromotion(beans300gTin, 3, 2));
		salesPromotionRepository.addSalesPromotion(new XForFixedAmountSalesPromotion(cokeCan, 2, sterling.createFromString("1")));
		DiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository);
		
		// Perform a checkout
		CheckoutResult result = new CheckoutCalculator(shoppingBasket, discountsCalculator).executeCheckout();
		logResult(shoppingBasket, result);
		
		// Assertions
		assertThat("Check sub total", result.getSubTotal(), is(sterling.createFromString("3.30")));
		// This is now correct as I have implemented SalesPromotions and Discounts
		assertThat("Check total to pay", result.getTotalToPay(), is(sterling.createFromString("2.40")));
	}
	
	/**
	 * testCheckout2_multipleSalesPromotionsForSameItem
	 * 
	 * We have a "3 for 2" and a "2 for 1" on beans, running at the same time
	 * The shopper buys 6 tins of beans
	 * This triggers the "3 for 2" twice, and the "2 for 1" three times 
	 * So they pay very little
	 * This may need addressing in future
	 */
	@Test
	public void testCheckout2_multipleSalesPromotionsForSameItem() {
		// Create our basket of items
		ShoppingBasket shoppingBasket = new SimpleShoppingBasket(sterling);
		for (int i=0; i < 6; i++) {
			shoppingBasket.addItem(new SimpleShoppingBasketItem(beans300gTin));
		}
		
		// Setup available sales promotions
		SimpleSalesPromotionRepository salesPromotionRepository = new SimpleSalesPromotionRepository();
		salesPromotionRepository.addSalesPromotion(new XForYSalesPromotion(beans300gTin, 2, 1));
		salesPromotionRepository.addSalesPromotion(new XForYSalesPromotion(beans300gTin, 3, 2));
		DiscountsCalculator discountsCalculator = new SimpleDiscountsCalculator(salesPromotionRepository);
		
		// Perform a checkout
		CheckoutResult result = new CheckoutCalculator(shoppingBasket, discountsCalculator).executeCheckout();
		logResult(shoppingBasket, result);
		
		// Assertions
		assertThat("Check sub total", result.getSubTotal(), is(sterling.createFromString("3.00")));
		// This is quite a small final bill - see method comments
		assertThat("Check total to pay", result.getTotalToPay(), is(sterling.createFromString("0.50")));
	}

}
