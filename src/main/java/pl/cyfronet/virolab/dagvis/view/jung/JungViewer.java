package pl.cyfronet.virolab.dagvis.view.jung;

import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.view.Viewer;

public class JungViewer implements Viewer {

	private static Logger log = Logger.getLogger(JungViewer.class);
	private edu.uci.ics.jung.graph.Graph<INode, IEdge> graph = new edu.uci.ics.jung.graph.DirectedSparseMultigraph<INode, IEdge>();

	@Override
	public void setGraph(IGraph in) {
		for (INode node : in.getNodes()) {
			graph.addVertex(node);
		}
		for (IEdge edge: in.getEdges()) {
			graph.addEdge(edge, edge.getSource(), edge.getTarget());
		}
		log.debug("Vertices: " + graph.getVertices());
		log.debug("Edges: " + graph.getEdges());
	}
	
	@Override
	public void view() {
		new JungViewerFrame(graph);
	}

}
