package cfs.supermarketpricing;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class Test1 {

	@Test
	public void test1() {
		assertThat("Should be 1.33", 1.33, CoreMatchers.is(1.33));
	}

}
