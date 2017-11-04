package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleDiscountRepository
 * 
 * Simple in-memory list of Discount instances
 * 
 * @author Connor
 */
public class SimpleSalesPromotionRepository implements SalesPromotionRepository {
	
	private final List<SalesPromotion> discounts = new ArrayList<>();
			
	
	public SimpleSalesPromotionRepository() {
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotionRepository#getSalesPromotions()
	 */
	@Override
	public List<SalesPromotion> getSalesPromotions() {
		return Collections.unmodifiableList(discounts);
	}
	
	public void addDiscount(SalesPromotion d) {
		discounts.add(d);
	}

}
