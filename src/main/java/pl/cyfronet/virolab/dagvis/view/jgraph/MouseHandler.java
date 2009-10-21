package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class MouseHandler implements MouseWheelListener {

	private static Logger log = Logger.getLogger(MouseHandler.class);
	private JGraph graph;
	
	public MouseHandler(JGraph graph) {
		this.graph = graph;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		log.trace(e);
		double factor = 1.1;
		if (e.getWheelRotation() < 0) {
			factor = 0.9;
		}
		graph.setScale(graph.getScale() * Math.abs(e.getWheelRotation())*factor);
	}

}
