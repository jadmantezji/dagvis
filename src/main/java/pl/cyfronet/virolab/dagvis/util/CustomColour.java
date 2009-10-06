package pl.cyfronet.virolab.dagvis.util;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class CustomColour extends Color {

	private static Logger log = Logger.getLogger(CustomColour.class);
	
	public CustomColour(int rgb) {
		super(rgb);
		// TODO Auto-generated constructor stub
	}
	
	private static Map<String, Color> parseMap = new HashMap<String, Color>();
	static {
		for (Field f : Color.class.getDeclaredFields()) {
			if (f.getType() == Color.class) {
				try {
					parseMap.put(f.getName().toLowerCase(), (Color) f.get(Color.class));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		parseMap.put("steelblue", new Color(0, 0, 200));
		parseMap.put("palegreen", new Color(0, 200, 0));
		parseMap.put("lightblue2", new Color(150, 150, 255));
		
	}
	
	public static Color parse(String s) {
		Color result = Color.BLACK;
		if (parseMap.containsKey(s)) {
			result = parseMap.get(s);
		} else if (s != null && !s.isEmpty()) {
			log.warn("Colour " + s + " could not recognized, defaults to " + Color.BLACK);
		}
		return result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7687808175536846457L;

}
