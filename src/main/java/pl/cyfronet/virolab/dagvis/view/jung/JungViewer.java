package pl.cyfronet.virolab.dagvis.view.jung;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.view.Viewer;

public class JungViewer implements Viewer {

	private static Logger log = Logger.getLogger(JungViewer.class);
	private edu.uci.ics.jung.graph.Graph<INode, IEdge> graph = new edu.uci.ics.jung.graph.DirectedSparseMultigraph<INode, IEdge>();

	public void setGraph(IGraph in) {
		Map<String, INode> nodes = new HashMap<String, INode>();
		for (INode node : in.getNodes()) {
			graph.addVertex(node);
			nodes.put(node.getName(), node);
		}
		for (IEdge edge: in.getEdges()) {
			INode source = nodes.get(edge.getSource().getName());
			INode target = nodes.get(edge.getTarget().getName());
			graph.addEdge(edge, source, target);
		}
		log.debug("Vertices: " + graph.getVertices());
		log.debug("Edges: " + graph.getEdges());
	}
	
	public void view() {
		new JungViewerFrame(graph);
	}

	public IGraph getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
