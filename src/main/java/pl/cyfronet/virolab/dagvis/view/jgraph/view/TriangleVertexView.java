package pl.cyfronet.virolab.dagvis.view.jgraph.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

import pl.cyfronet.virolab.dagvis.view.jgraph.CustomGraphConstants;

public class TriangleVertexView extends VertexView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3802621431476365876L;
	//	private static Logger log = Logger.getLogger(TriangleVertexView.class);
	private static transient JGraphTriangleRenderer renderer = new JGraphTriangleRenderer();
	
	public TriangleVertexView(Object graphCell) {
		super(graphCell);
	}
	
	public CellViewRenderer getRenderer() {
		return renderer;
	}

	/**
	 * Returns the intersection of the bounding rectangle and the
	 * straight line between the source and the specified point p.
	 * The specified point is expected not to intersect the bounds.
	 */
	@Override
	public Point2D getPerimeterPoint(EdgeView edge, Point2D source, Point2D p) {
		Rectangle2D bounds = getBounds();

		double x = bounds.getX();
		double y = bounds.getY();
		double width = bounds.getWidth();
		double height = bounds.getHeight();
		Point2D center = AbstractCellView.getCenterPoint(this);
		Point2D top = new Point2D.Double(center.getX(), y);
		Point2D left = new Point2D.Double(x, y + height);
		Point2D right = new Point2D.Double(x + width, y + height);

		Point2D i;
		if ((distanceFromLine(center, right, p) > 0)
				&& (p.getX() > center.getX())) {
			i = intersection(p, center, top, right);
		} else if ((distanceFromLine(center, left, p) > 0)
				&& (p.getX() < center.getX())) {
			i = intersection(p, center, top, left);
		} else {
			i = intersection(p, center, right, left);
		}
		return i;
	}

	protected double distanceFromLine(Point2D lineOneStart, Point2D lineOneEnd,
			Point2D p) {
		double m = (double) (lineOneEnd.getY() - lineOneStart.getY())
				/ (double) (lineOneEnd.getX() - lineOneStart.getX());
		double b = lineOneStart.getY() - m * lineOneStart.getX();
		// distance from line d = ( (mx - y + b) / sgrt(1 + m^2))
		return (m * p.getX() - p.getY() + b) / (Math.sqrt(1 + m * m));
	}

	protected Point2D intersection(Point2D lineOneStart, Point2D lineOneEnd,
			Point2D lineTwoStart, Point2D lineTwoEnd) {
		// m = delta y / delta x, the slope of a line
		// b = y - mx, the axis intercept
		double m1 = (double) (lineOneEnd.getY() - lineOneStart.getY())
				/ (double) (lineOneEnd.getX() - lineOneStart.getX());
		double b1 = lineOneStart.getY() - m1 * lineOneStart.getX();
		double m2 = (double) (lineTwoEnd.getY() - lineTwoStart.getY())
				/ (double) (lineTwoEnd.getX() - lineTwoStart.getX());
		double b2 = lineTwoStart.getY() - m2 * lineTwoStart.getX();
		double xinter = (b1 - b2) / (m2 - m1);
		double yinter = m1 * xinter + b1;
		Point2D intersection = new Point2D.Double(xinter, yinter);
		return intersection;
	}

	/**
	 * The renderer for this view
	 */
	public static class JGraphTriangleRenderer extends VertexRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5649480922068921315L;

		/**
		 * Return a slightly larger preferred size than for a rectangle.
		 */
		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			d.width += d.width/8;
			d.height += d.height/2;
			return d;
		}
		
		/**
		 * The method that draws the cell
		 */
		public void paint(Graphics g) {
			int b = borderWidth;
			Graphics2D g2 = (Graphics2D) g;
			Dimension d = getSize();
			boolean tmp = selected;
			Polygon p = new Polygon();
			p.addPoint(b, d.height - b);
			p.addPoint(d.width - b, d.height - b);
			p.addPoint(b + d.width/2, b);
			
			if (super.isOpaque()) {
				g.setColor(super.getBackground());
				if (gradientColor != null && !preview) {
					setOpaque(false);
					g2.setPaint(new GradientPaint(0, 0, getBackground(), getWidth(),
							getHeight(), gradientColor, true));
				}
				g.fillPolygon(p);
			}
			try {
				setBorder(null);
				setOpaque(false);
				selected = false;
				super.paint(g);
			} finally {
				selected = tmp;
			}
			if (bordercolor != null) {
				g.setColor(bordercolor);
				g2.setStroke(new BasicStroke(b));
				g.drawPolygon(p);
			}
			if (selected) {
				g2.setStroke(CustomGraphConstants.SELECTION_STROKE);
				g.setColor(highlightColor);
				g.drawPolygon(p);
			}
		}
	}
	
}
