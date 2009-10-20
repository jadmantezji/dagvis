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

	public static String processLabel(String value) {
		String result = value;
		if (value != null && value.indexOf('|') != -1) {
			result = value.split("\\|")[0];
		}
		return result;
	}
	
/*
	public static String fromDot(String value) {
		String[] strings = value.split("\\|");
		String[][] table = new String[strings.length][];
		for (int i = 0; i < strings.length; i++) {
			byte[] b = strings[i].getBytes();
			for (int j = 0; j < b.length; j++) {
				if (b[j] == '\\' && j < b.length-1 && b[j+1] == 'l') {
					b[j] = '*';
					b[j+1] = '*';
				}
			}
			String s2 = new String(b);
			s2 = s2.replaceAll("\\*\\*", "\n");
			if (s2.endsWith("\n")) {
				s2 = s2.substring(0, s2.length()-1);
			}
			table[i] = s2.split("\n");
		}
		int max = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i].length > max) max = table[i].length;
		}
		int[] width = new int[table.length];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j].length() > width[i]) {
					width[i] = table[i][j].length();
				}
			}
			//System.out.println(lmax);
		}
		StringBuilder sb = new StringBuilder("<html>");
		for (int i = 0; i < max; i++) {
			for (int x = 0; x < table.length; x++) {
				if (i < table[x].length) {
					sb.append(table[x][i]);
					for (int k = 0; k < width[x] - table[x][i].length(); k++)
						sb.append(' ');
				} else {
					for (int k = 0; k < width[x]; k++) {
						sb.append(' ');
					}
				}
				if (x < table.length-1)
					sb.append('|');
				else if (i < max-1)
					sb.append("<br>");
			}
		}
		sb.append("</html>");
		return sb.toString();
	}
*/
	
}
