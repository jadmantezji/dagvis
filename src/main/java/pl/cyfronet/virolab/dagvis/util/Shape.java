package pl.cyfronet.virolab.dagvis.util;

public enum Shape {
	
	BOX, ELLIPSE;
	
	public static Shape parse(String text) {
		Shape result = Shape.ELLIPSE;
		if (text != null && text.equals("box")) {
			result = Shape.BOX;
		}
		return result;
	}

}
