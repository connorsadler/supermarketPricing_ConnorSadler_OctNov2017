package cfs.supermarketpricing.money;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * SterlingAmountTest
 * 
 * @author Connor
 */
public class SterlingAmountTest {
	
	private final SterlingMoneySystem sterling = new SterlingMoneySystem();

	/**
	 * testEquals
	 * 
	 * Simple test of SterlingAmount equals
	 */
	@Test
	public void testEquals() {
		SterlingAmount first_oneNinetyNine = new SterlingAmount(1, 99);
		SterlingAmount second_oneNinetyNine = new SterlingAmount(1, 99);
		assertThat(first_oneNinetyNine, is(second_oneNinetyNine));
		
		SterlingAmount third_twoFifty = new SterlingAmount(2, 50);
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
		assertThat(new SterlingAmount(0,  0).plus(new SterlingAmount(0,  0)),
				is(new SterlingAmount(0,  0)));
		assertThat(new SterlingAmount(1,  0).plus(new SterlingAmount(0,  0)),
				is(new SterlingAmount(1,  0)));
		assertThat(new SterlingAmount(2,  13).plus(new SterlingAmount(0,  0)),
				is(new SterlingAmount(2,  13)));
		assertThat(new SterlingAmount(1,  56).plus(new SterlingAmount(0,  0)),
				is(new SterlingAmount(1,  56)));
		assertThat(new SterlingAmount(0,   0).plus(new SterlingAmount(1, 41)),
				is(new SterlingAmount(1,  41)));

		// Test non zeros
		assertThat(new SterlingAmount(3,  50).plus(new SterlingAmount(11, 41)),
				is(new SterlingAmount(14, 91)));
		
		// Overflow pence > 100, which overflows into another pound
		assertThat(new SterlingAmount(1, 99).plus(new SterlingAmount(1, 99)),
				is(new SterlingAmount(3, 98)));
		
		// Test negative amount added
		assertThat(new SterlingAmount(1,  56).plus(new SterlingAmount(0, -90)),
				is(new SterlingAmount(0,  66)));
	}

	/**
	 * testCreateFromBigDecimal
	 */
	@Test
	public void testCreateFromBigDecimal() {
		assertThat(sterling.createFromBigDecimal(new BigDecimal("0")),
				is(new SterlingAmount( 0,  0)));
		assertThat(sterling.createFromBigDecimal(new BigDecimal("1")),
				is(new SterlingAmount( 1,  0)));
		assertThat(sterling.createFromBigDecimal(new BigDecimal("11.23")),
				is(new SterlingAmount(11, 23)));
	}
	
	/**
	 * testToBigDecimal
	 */
	@Test
	public void testToBigDecimal() {
		assertThat(new SterlingAmount( 0,  0).toBigDecimal(),
				is(new BigDecimal("0")));
		assertThat(new SterlingAmount( 1,  0).toBigDecimal(),
				is(new BigDecimal("1")));
		assertThat(new SterlingAmount(11, 23).toBigDecimal(),
				is(new BigDecimal("11.23")));
		
	}
	
	/**
	 * testTimes
	 * 
	 * Simple test of SterlingAmount times
	 */
	@Test
	public void testTimes() {
		// x 0
		assertThat(new SterlingAmount(0,  0).times(0.0d),
				is(new SterlingAmount(0,  0)));
		assertThat(new SterlingAmount(2,  34).times(0.0d),
				is(new SterlingAmount(0,  0)));
		// x 1
		assertThat(new SterlingAmount(0,  0).times(1.0d),
				is(new SterlingAmount(0,  0)));
		assertThat(new SterlingAmount(1,  45).times(1.0d),
				is(new SterlingAmount(1,  45)));
		
		// x decimal > 1
		assertThat(new SterlingAmount(1,  45).times(3.5d),
				is(new SterlingAmount(5,   8))); // 5.075 -> rounded up
		assertThat(new SterlingAmount(1,  99).times(0.2d),
				is(new SterlingAmount(0,  40)));
		// x decimal < 1
		assertThat(new SterlingAmount(1,  45).times(0.2d),
				is(new SterlingAmount(0,  29)));
		
		// Test rounding
		assertThat(new SterlingAmount(1,  00).times(0.3d),
				is(new SterlingAmount(0,  30))); // 0.30
		assertThat(new SterlingAmount(0,  99).times(0.3d),
				is(new SterlingAmount(0,  30))); // 29.7 -> rounds up
		assertThat(new SterlingAmount(0,  98).times(0.3d),
				is(new SterlingAmount(0,  29))); // 29.4 -> rounds down
	}
		
}
