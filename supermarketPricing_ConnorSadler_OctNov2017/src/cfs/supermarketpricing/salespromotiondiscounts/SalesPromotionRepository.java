package cfs.supermarketpricing.salespromotiondiscounts;

import java.util.List;

/**
 * SalesPromotionRepository
 * 
 * @author Connor
 */
public interface SalesPromotionRepository {
	
	/**
	 * getSalesPromotions
	 * 
	 * Should return an immutable List of available SalesPromotions
	 * Not all of these will apply to any given ShoppingBasket
	 */
	public List<SalesPromotion> getSalesPromotions();

}
