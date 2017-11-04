package cfs.supermarketpricing.salespromotiondiscounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.discounts.DiscountsCalculationResult;
import cfs.supermarketpricing.discounts.DiscountsCalculator;
import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * SimpleDiscountsCalculator
 * 
 * @author Connor
 */
public class SimpleDiscountsCalculator implements DiscountsCalculator {
	// Our list of SalesPromotions
	private SalesPromotionRepository salesPromotionRepository;

	public SimpleDiscountsCalculator(SalesPromotionRepository salesPromotionRepository) {
		this.salesPromotionRepository = salesPromotionRepository;
	}

	/**
	 * @see cfs.supermarketpricing.discounts.DiscountsCalculator#executeDiscountsCalculation(cfs.supermarketpricing.basket.ShoppingBasket)
	 */
	@Override
	public DiscountsCalculationResult executeDiscountsCalculation(ShoppingBasket shoppingBasket) {
		// Iterate through list of SalesPromotions to accumulate Discounts
		List<SalesPromotion> allSalesPromotions = salesPromotionRepository.getSalesPromotions();
		List<Discount> allDiscountsApplied = new ArrayList<>();
		for (SalesPromotion salesPromotion : allSalesPromotions) {
			// Check which ones apply - and to which basket items
			// Each SalesPromotions can apply itself more than once to give multiple discounts
			SalesPromotionChecker checker = salesPromotion.getSalesPromotionChecker();
			List<Discount> discountsForPromotion = checker.executeCheck(shoppingBasket);
			allDiscountsApplied.addAll(discountsForPromotion);
		}

		// Total the amounts up
		// NOTE: The result should be negative
		MoneySystem<? extends MonetaryAmount> moneySystem = shoppingBasket.getMoneySystem();
		MonetaryAmount discountTotal = moneySystem.createFromBigDecimal(new BigDecimal("0"));
		for (Discount discount : allDiscountsApplied) {
			discountTotal = discountTotal.plus(discount.getDiscountAmount());
		}

		return new SimpleDiscountsCalculationResult(allDiscountsApplied, discountTotal);
	}
}
