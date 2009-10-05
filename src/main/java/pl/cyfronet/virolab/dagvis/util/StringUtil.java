package pl.cyfronet.virolab.dagvis.util;

import java.awt.Dimension;
import java.awt.FontMetrics;

public class StringUtil {

	public static int lines(String text) {
		int lines = 0;
		int lastIndex = 0;
		while ((lastIndex = text.indexOf('\n', lastIndex)) != -1) {
			lines++;
			lastIndex++;
		}
		return lines + 1;
	}
	
	public static Dimension size(FontMetrics metrics, String text) {
		int width = metrics.stringWidth(text);
		int height = metrics.getHeight() * lines(text);
		return new Dimension(width, height);
	}

}
