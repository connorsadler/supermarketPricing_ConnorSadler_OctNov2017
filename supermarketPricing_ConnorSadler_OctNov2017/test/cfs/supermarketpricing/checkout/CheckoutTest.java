package cfs.supermarketpricing.checkout;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasket;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItem;
import cfs.supermarketpricing.basket.SimpleShoppingBasketItemWithWeight;
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

public class CheckoutTest {
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
	 * testCheckout1
	 */
	@Test
	public void testCheckout1() {
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
		
		// Assertions
		assertThat("Check sub total", result.getSubTotal(), is(sterling.createFromString("3.30")));
		// This is now correct as I have implemented SalesPromotions and Discounts
		assertThat("Check total to pay", result.getTotalToPay(), is(sterling.createFromString("2.40")));
	}

}
