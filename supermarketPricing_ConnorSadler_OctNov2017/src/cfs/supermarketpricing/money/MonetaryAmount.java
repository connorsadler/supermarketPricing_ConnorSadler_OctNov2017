package cfs.supermarketpricing.money;

/**
 * An amount of money
 * e.g. £10.99
 * 
 * In reality this may be linked to some sort of monetary system e.g. Sterling
 * 
 * Implementations should be immutable and new instances created when calculations are performed
 * 
 * @author Connor
 *
 */
public interface MonetaryAmount {

	/**
	 * plus
	 * 
	 * Add two amounts together and return a new result
	 * Does not modify the current instance as we're immutable
	 */
	MonetaryAmount plus(MonetaryAmount amount);

}
