package pl.cyfronet.virolab.dagvis.view.jung.transformers;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.util.StringUtil;

import com.alexmerz.graphviz.objects.Node;

public class ShapeTransformer implements Transformer<Node, Shape> {

	private static Logger log = Logger.getLogger(ShapeTransformer.class);
	private FontMetrics metrics;
	
	public ShapeTransformer(FontMetrics metrics) {
		this.metrics = metrics;
	}
	
	public Shape transform(Node input) {
		String label = input.getAttribute("label");
		String shape = input.getAttribute("shape");
		Dimension dim = StringUtil.size(metrics, label);
		Shape result = null;
		if (shape.equals("ellipse")) {
			result = new Ellipse2D.Double(-dim.getHeight()/2, -dim.getWidth()/2, dim.getHeight(), dim.getWidth());
		} else if (shape.equals("box")) {
			result = new Rectangle2D.Double(-dim.getHeight()/2, -dim.getWidth()/2, dim.getHeight(), dim.getWidth());
		} else {
			log.warn("Not implemented shape visualization: " + shape);
		}
		return result;
	}

}
