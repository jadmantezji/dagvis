package pl.cyfronet.virolab.dagvis.view.jpowergraph;

import net.sourceforge.jpowergraph.Edge;
import net.sourceforge.jpowergraph.Node;
import net.sourceforge.jpowergraph.defaults.DefaultGraph;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class JPGGraph extends DefaultGraph {
	
	public void addNode(Node node) {
		m_nodes.add(node);
	}
	
	public void addEdge(Edge edge) {
		m_edges.add(edge);
	}

}
