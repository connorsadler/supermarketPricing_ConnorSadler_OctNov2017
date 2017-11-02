package cfs.supermarketpricing.money;

/**
 * SterlingFactory
 * 
 * Creates instances of Sterling amounts
 * 
 * @author Connor
 */
public class SterlingFactory {

	/**
	 * createPoundsAmount
	 */
	public static SterlingAmount createPoundsAmount(int pounds, int pence) {
		return new SterlingAmount(pounds, pence);
	}

}
