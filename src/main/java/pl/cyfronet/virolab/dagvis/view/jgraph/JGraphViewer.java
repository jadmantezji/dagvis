package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.Port;
import org.jgraph.graph.PortView;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.view.Viewer;

public class JGraphViewer implements Viewer {

	private static Logger log = Logger.getLogger(JGraphViewer.class);
	private GraphModel model = new DefaultGraphModel();
	private GraphLayoutCache view = new GraphLayoutCache(model, new ViewFactory());
	private JGraph graph = new JGraph(model, view);
	private Map<GraphCell, Map<Object, Object>> nestedMap;

	static {
		PortView.allowPortMagic = false;
	}

	public void setGraph(IGraph in) {
		nestedMap = new HashMap<GraphCell, Map<Object, Object>>();
		Map<String, DefaultGraphCell> cells = new HashMap<String, DefaultGraphCell>();
		for (INode node : in.getNodes()) {
			String key = node.getName();
			DefaultGraphCell cell = new DefaultGraphCell(node);
			createNodeAttributes(cell.getAttributes(), node);
			cells.put(key, cell);
			nestedMap.put(cell, cell.getAttributes());
			cell.addPort();
		}
		for (IEdge edge : in.getEdges()) {
			String sourceId = edge.getSource().getName();
			String targetId = edge.getTarget().getName();
			DefaultEdge de = new DefaultEdge(edge);
			// DefaultPort sourcePort = new DefaultPort();
			// DefaultPort targetPort = new DefaultPort();
			// cells.get(sourceId).add(sourcePort);
			// cells.get(targetId).add(targetPort);
			Port sourcePort = (Port) cells.get(sourceId).getFirstChild();
			Port targetPort = (Port) cells.get(targetId).getFirstChild();
			de.setSource(sourcePort);
			de.setTarget(targetPort);
			createEdgeAttributes(de.getAttributes(), edge);
			cells.put(sourceId + targetId, de);
			nestedMap.put(de, de.getAttributes());
		}
		view.insert(cells.values().toArray());
		// log.info("Model: " + graph.getModel());
	}

	private void createNodeAttributes(Map attributes, INode node) {
		CustomGraphConstants.setBorderColor(attributes, node.getColor());
		CustomGraphConstants.setLineWidth(attributes, node.isBold());
		String label = node.toString();
		CustomGraphConstants.setShape(attributes, node.getShape(), label);
	}

	private void createEdgeAttributes(Map attributes, IEdge edge) {
		CustomGraphConstants.setLineStyle(attributes, CustomGraphConstants.STYLE_SPLINE);
		CustomGraphConstants.setLineEnd(attributes, edge.getArrowStyle());
		CustomGraphConstants.setLineColor(attributes, edge.getColor());
		CustomGraphConstants.setLineWidth(attributes, edge.isBold());
		CustomGraphConstants.setLinePattern(attributes, edge.getLinePattern());
	}
	
	public void view() {
		new JGraphViewerFrame(graph, this);
	}

	public IGraph getGraph() {
		// TODO
		return null;
	}

}
