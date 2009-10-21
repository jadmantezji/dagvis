package pl.cyfronet.virolab.dagvis.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class StringUtilsTest {
	
	@Test
	public void lines() {
		String s = "raz\ndwa\ntrzy";
		assertEquals(StringUtil.lines(s), 3);
	}

}
