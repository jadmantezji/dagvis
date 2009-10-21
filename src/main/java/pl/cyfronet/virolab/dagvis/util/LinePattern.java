package pl.cyfronet.virolab.dagvis.util;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public enum LinePattern {
	
	CONTINUOUS, DASHED, DOTTED;
	
	public static LinePattern parse(String text) {
		LinePattern result = LinePattern.CONTINUOUS;
		if (text == null) return result;
		if (text.equals("dashed")) {
			result = LinePattern.DASHED;
		} else if (text.equals("dotted")) {
			result = LinePattern.DOTTED;
		}
		return result;
	}

}
