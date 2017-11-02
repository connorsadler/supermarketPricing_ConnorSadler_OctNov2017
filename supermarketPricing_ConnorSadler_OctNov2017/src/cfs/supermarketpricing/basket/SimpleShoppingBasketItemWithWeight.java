package cfs.supermarketpricing.basket;

import cfs.supermarketpricing.sku.StockKeepingUnit;

/**
 * SimpleShoppingBasketItemWithWeight
 * 
 * Simple impl of ShoppingBasketItemWithWeight
 * 
 * @author Connor
 */
public class SimpleShoppingBasketItemWithWeight extends SimpleShoppingBasketItem implements ShoppingBasketItemWithWeight {

	private double weight;

	/**
	 * Constructor
	 */
	public SimpleShoppingBasketItemWithWeight(StockKeepingUnit stockKeepingUnit, double weight) {
		super(stockKeepingUnit);
		this.weight = weight;
	}
	
	/**
	 * @see cfs.supermarketpricing.basket.ShoppingBasketItemWithWeight#getWeight()
	 */
	@Override
	public double getWeight() {
		return weight;
	}


}
