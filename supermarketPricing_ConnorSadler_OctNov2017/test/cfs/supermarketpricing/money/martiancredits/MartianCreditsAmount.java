package cfs.supermarketpricing.money.martiancredits;

import java.math.BigDecimal;

import cfs.supermarketpricing.money.MonetaryAmount;

/**
 * MartianCreditsAmount
 * 
 * A test alternate MoneySystem amount
 * 
 * @author Connor
 */
public class MartianCreditsAmount implements MonetaryAmount {

	private BigDecimal value;

	/**
	 * Constructor
	 */
	public MartianCreditsAmount(BigDecimal value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @see cfs.supermarketpricing.money.MonetaryAmount#plus(cfs.supermarketpricing.money.MonetaryAmount)
	 */
	@Override
	public MonetaryAmount plus(MonetaryAmount amount) {
		throw new UnsupportedOperationException("Not supported");
	}

	/**
	 * @see cfs.supermarketpricing.money.MonetaryAmount#times(double)
	 */
	@Override
	public MonetaryAmount times(double d) {
		throw new UnsupportedOperationException("Not supported");
	}
}
