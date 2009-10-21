package pl.cyfronet.virolab.dagvis.util;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public enum Shape {
	
	BOX, ELLIPSE, CIRCLE, TRIANGLE;
	
	public static Shape parse(String text) {
		Shape result = Shape.ELLIPSE;
		if (text == null) return result;
		if (text.equals("box")) {
			result = Shape.BOX;
		} else if (text.equals("circle")) {
			result = Shape.CIRCLE;
		} else if (text.equals("triangle")) {
			result = Shape.TRIANGLE;
		}
		return result;
	}

}
