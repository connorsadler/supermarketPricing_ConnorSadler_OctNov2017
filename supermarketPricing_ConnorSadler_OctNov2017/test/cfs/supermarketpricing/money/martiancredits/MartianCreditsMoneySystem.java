package cfs.supermarketpricing.money.martiancredits;

import java.math.BigDecimal;

import cfs.supermarketpricing.money.MonetaryAmount;
import cfs.supermarketpricing.money.MoneySystem;

/**
 * MartianCreditsMoneySystem
 * 
 * A test alternate MoneySystem amount
 * 
 * @author Connor
 */
public class MartianCreditsMoneySystem implements MoneySystem<MartianCreditsAmount> {

	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#getDescription()
	 */
	@Override
	public String getDescription() {
		return "MartianCreditsMoneySystem";
	}

	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#createFromBigDecimal(java.math.BigDecimal)
	 */
	@Override
	public MartianCreditsAmount createFromBigDecimal(BigDecimal bd) {
		return new MartianCreditsAmount(bd);
	}

	/**
	 * @see cfs.supermarketpricing.money.MoneySystem#isMatchingCurrency(cfs.supermarketpricing.money.MonetaryAmount)
	 */
	@Override
	public boolean isMatchingCurrency(MonetaryAmount amount) {
		return amount instanceof MartianCreditsAmount;
	}

}
