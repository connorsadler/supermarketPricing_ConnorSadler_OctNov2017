package cfs.supermarketpricing.money;

import java.math.BigDecimal;

/**
 * SterlingMoneySystem
 * 
 * Sterling impl of MoneySystem
 * 
 * @author Connor
 */
public class SterlingMoneySystem implements MoneySystem<SterlingAmount> {

	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Sterling";
	}
	
	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#createFromBigDecimal(java.math.BigDecimal)
	 */
	@Override
	public SterlingAmount createFromBigDecimal(BigDecimal bd) {
		return SterlingAmount.createFromBigDecimal(bd);
	}

	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#isMatchingCurrency(cfs.supermarketpricing.money.MonetaryAmount)
	 */
	@Override
	public boolean isMatchingCurrency(MonetaryAmount amount) {
		return amount instanceof SterlingAmount;
	}

}
