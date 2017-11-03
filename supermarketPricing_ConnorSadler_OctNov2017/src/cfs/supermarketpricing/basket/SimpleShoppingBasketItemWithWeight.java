package cfs.supermarketpricing.basket;

import cfs.supermarketpricing.money.MonetaryAmount;
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
	
	/**
	 * @see cfs.supermarketpricing.basket.SimpleShoppingBasketItem#calcAmount()
	 */
	@Override
	public MonetaryAmount calcAmount() {
		// For an item with a weight, we multiply the base price by the weight
		// e.g. Oranges 0.200 kg @  £1.99/kg = £ 0.40
		MonetaryAmount basePrice = getStockKeepingUnit().getPrice();
		return basePrice.times(weight);
	}


}
