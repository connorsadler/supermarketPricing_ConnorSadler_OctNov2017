package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimpleSalesPromotionRepository
 * 
 * Simple in-memory list of SalesPromotion instances
 * 
 * @author Connor
 */
public class SimpleSalesPromotionRepository implements SalesPromotionRepository {
	
	private final List<SalesPromotion> salesPromotions = new ArrayList<>();
			
	
	public SimpleSalesPromotionRepository() {
	}

	/**
	 * @see cfs.supermarketpricing.salespromotiondiscounts.SalesPromotionRepository#getSalesPromotions()
	 */
	@Override
	public List<SalesPromotion> getSalesPromotions() {
		return Collections.unmodifiableList(salesPromotions);
	}
	
	public void addSalesPromotion(SalesPromotion d) {
		salesPromotions.add(d);
	}

}
