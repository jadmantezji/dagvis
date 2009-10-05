package pl.cyfronet.virolab.dagvis.view.jpowergraph;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.jpowergraph.example.edges.ArrowEdge;
import net.sourceforge.jpowergraph.example.nodes.NodeType1;

import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.view.Viewer;

public class JPGViewer implements Viewer {

	private static Logger log = Logger.getLogger(JPGViewer.class);
	private JPGGraph graph = new JPGGraph();
	
	@Override
	public void setGraph(IGraph in) {
		Map<String, NodeType1> nodes = new HashMap<String, NodeType1>();
		for (INode node : in.getNodes()) {
			NodeType1 nodeToInsert = new NodeType1(node.getName());
			nodes.put(node.getName(), nodeToInsert);
			graph.addNode(nodeToInsert);
		}
		for (IEdge edge: in.getEdges()) {
			String sourceId = edge.getSource().getName();
			String targetId = edge.getTarget().getName();
			graph.addEdge(new ArrowEdge(nodes.get(sourceId), nodes.get(targetId)));
		}
		log.info("Vertices: " + graph.getAllNodes());
		log.info("Edges: " + graph.getAllEdges());
		
	}

	@Override
	public void view() {
		// to be implemented
	}

}
