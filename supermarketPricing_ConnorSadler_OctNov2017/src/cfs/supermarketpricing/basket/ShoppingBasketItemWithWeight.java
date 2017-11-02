package cfs.supermarketpricing.basket;

/**
 * ShoppingBasketItemWithWeight
 * 
 * An item which needs a weight e.g. oranges @ 0.200 kg
 * 
 * @author Connor
 */
public interface ShoppingBasketItemWithWeight extends ShoppingBasketItem {

	/**
	 * getWeight
	 * 
	 * The weight of the item you've put in your basket
	 */
	public double getWeight();
}
