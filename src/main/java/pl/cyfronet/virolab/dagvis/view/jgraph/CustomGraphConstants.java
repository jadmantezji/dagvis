package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.jgraph.graph.GraphConstants;

import pl.cyfronet.virolab.dagvis.util.ArrowStyle;
import pl.cyfronet.virolab.dagvis.util.LinePattern;
import pl.cyfronet.virolab.dagvis.util.Shape;

import com.jgraph.layout.JGraphCompoundLayout;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.graph.JGraphSimpleLayout;
import com.jgraph.layout.graph.JGraphSpringLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.layout.organic.JGraphFastOrganicLayout;
import com.jgraph.layout.organic.JGraphOrganicLayout;
import com.jgraph.layout.organic.JGraphSelfOrganizingOrganicLayout;
import com.jgraph.layout.simple.SimpleGridLayout;
import com.jgraph.layout.tree.JGraphCompactTreeLayout;
import com.jgraph.layout.tree.JGraphRadialTreeLayout;
import com.jgraph.layout.tree.JGraphTreeLayout;

public class CustomGraphConstants extends GraphConstants {
	
	// TODO:
	// we have to obtain FontMetrics in order to determine the size of the cell
	// probably there is a better way to do it
	private static BufferedImage bi = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
	private static Graphics g = GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics(bi);
	private static FontMetrics metrics = g.getFontMetrics(CustomGraphConstants.DEFAULTFONT);
	
	public static final String SHAPE = "shape";
	
	public static final Map<String, JGraphLayout> layouts = new HashMap<String, JGraphLayout>();
	static {
		layouts.put("Hierarchical", new JGraphHierarchicalLayout());
		layouts.put("Compound", new JGraphCompoundLayout());
		layouts.put("CompactTree", new JGraphCompactTreeLayout());
		layouts.put("Tree", new JGraphTreeLayout());
		layouts.put("RadialTree", new JGraphRadialTreeLayout());
		layouts.put("Organic", new JGraphOrganicLayout());
		layouts.put("FastOrganic", new JGraphFastOrganicLayout());
		layouts.put("SelfOrganizingOrganic", new JGraphSelfOrganizingOrganicLayout());
		layouts.put("SimpleCircle", new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_CIRCLE));
		layouts.put("SimpleTilt", new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_TILT));
		layouts.put("SimpleRandom", new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_RANDOM));
		layouts.put("Spring", new JGraphSpringLayout());
		layouts.put("Grid", new SimpleGridLayout());
	}
		
	public static final Shape getShape(Map attributes) {
		Shape shape = (Shape) attributes.get(SHAPE);
		if (shape == null)
			shape = Shape.ELLIPSE;
		return shape;
	}
	
	public static final void setShape(Map attributes, Shape shape, String label) {
		attributes.put(SHAPE, shape);
		int width = metrics.stringWidth(label);
		int height = metrics.getHeight();
		Rectangle2D.Double bounds = null;
		if (shape == Shape.CIRCLE) {
			int max = Math.max(width, height) + 15;
			bounds = new Rectangle2D.Double(0, 0, max, max);
		} else if (shape == Shape.ELLIPSE) { 
			bounds = new Rectangle2D.Double(0, 0, width + 35, height + 20);
		} else if (shape == Shape.TRIANGLE) {
			bounds = new Rectangle2D.Double(0, 0, width + 80, height + 25);
		}
		setBounds(attributes, bounds);
	}
	
	public static final void setLineWidth(Map map, String style) {
		if (style == null || style.equals("") || style.equals("solid")) {
			map.put(LINEWIDTH, (float) 1.0);
		} else if (style.equals("bold")) {
			map.put(LINEWIDTH, (float) 3.5);
		}
	}
	
	public static final void setLineWidth(Map map, boolean bold) {
		if (!bold) {
			map.put(LINEWIDTH, (float) 1.0);
		} else {
			map.put(LINEWIDTH, (float) 3.5);
		}
	}
	
	public static final void setLineEnd(Map map, String style) {
		if (style == null || style.equals("") || style.equals("normal")) {
			map.put(LINEEND, ARROW_CLASSIC);
			map.put(ENDFILL, true);
		}
	}
	
	public static final void setLineEnd(Map map, ArrowStyle style) {
		if (style == null || style == ArrowStyle.NORMAL) {
			map.put(LINEEND, ARROW_CLASSIC);
			map.put(ENDFILL, true);
		}
	}
	
	public static final void setLinePattern(Map map, LinePattern style) {
		if (style == LinePattern.DASHED) {
			map.put(DASHPATTERN, new float[] {(float)6.0, (float)3.0} );
		} else if (style == LinePattern.DOTTED) {
			map.put(DASHPATTERN, new float[] {(float)2.0, (float)6.0} );
		}
	}
	
}
