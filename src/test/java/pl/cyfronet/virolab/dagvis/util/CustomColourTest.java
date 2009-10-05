package pl.cyfronet.virolab.dagvis.util;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class CustomColourTest {
	
	@Test
	public void parse() {
		assertEquals(Color.BLUE, CustomColour.parse("blue"));
	}

}
