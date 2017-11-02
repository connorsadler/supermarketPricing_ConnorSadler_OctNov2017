package cfs.supermarketpricing.money;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * SterlingAmountTest
 * 
 * @author Connor
 */
public class SterlingAmountTest {

	/**
	 * testEquals
	 * 
	 * Simple test of SterlingAmount equals
	 */
	@Test
	public void testEquals() {
		SterlingAmount first_oneNinetyNine = SterlingFactory.createPoundsAmount(1, 99);
		SterlingAmount second_oneNinetyNine = SterlingFactory.createPoundsAmount(1, 99);
		assertThat(first_oneNinetyNine, is(second_oneNinetyNine));
		
		SterlingAmount third_twoFifty = SterlingFactory.createPoundsAmount(2, 50);
		assertThat(first_oneNinetyNine, is(not(third_twoFifty)));
	}
	
	/**
	 * testPlus
	 * 
	 * Simple test of SterlingAmount plus
	 */
	@Test
	public void testPlus() {
		// Test zeros
		assertThat(SterlingFactory.createPoundsAmount(0,  0).plus(SterlingFactory.createPoundsAmount(0,  0)),
				is(SterlingFactory.createPoundsAmount(0,  0)));
		assertThat(SterlingFactory.createPoundsAmount(1,  0).plus(SterlingFactory.createPoundsAmount(0,  0)),
				is(SterlingFactory.createPoundsAmount(1,  0)));
		assertThat(SterlingFactory.createPoundsAmount(2,  13).plus(SterlingFactory.createPoundsAmount(0,  0)),
				is(SterlingFactory.createPoundsAmount(2,  13)));
		assertThat(SterlingFactory.createPoundsAmount(1,  56).plus(SterlingFactory.createPoundsAmount(0,  0)),
				is(SterlingFactory.createPoundsAmount(1,  56)));
		assertThat(SterlingFactory.createPoundsAmount(0,   0).plus(SterlingFactory.createPoundsAmount(1, 41)),
				is(SterlingFactory.createPoundsAmount(1,  41)));
		
		// Overflow pence > 100, which overflows into another pound
		assertThat(SterlingFactory.createPoundsAmount(1, 99).plus(SterlingFactory.createPoundsAmount(1, 99)),
				is(SterlingFactory.createPoundsAmount(3, 98)));
	}
}
