package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.List;

import cfs.supermarketpricing.basket.ShoppingBasket;
import cfs.supermarketpricing.discounts.DiscountsCalculationResult;
import cfs.supermarketpricing.discounts.DiscountsCalculator;

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
		DiscountList allDiscountsApplied = new SimpleDiscountList(shoppingBasket.getMoneySystem());
		for (SalesPromotion salesPromotion : allSalesPromotions) {
			// Check which ones apply - and to which basket items
			// Each SalesPromotions can apply itself more than once to give multiple discounts
			SalesPromotionChecker checker = salesPromotion.getSalesPromotionChecker();
			DiscountList discountsForPromotion = checker.executeCheck(shoppingBasket);
			allDiscountsApplied.addAll(discountsForPromotion);
		}

		return new SimpleDiscountsCalculationResult(allDiscountsApplied);
	}
}
