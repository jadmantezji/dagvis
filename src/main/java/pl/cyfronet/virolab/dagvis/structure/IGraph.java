package pl.cyfronet.virolab.dagvis.structure;

import java.util.Collection;

public interface IGraph {
	
	Collection<INode> getNodes();
	Collection<IEdge> getEdges();
	void addNode(INode node);
	void addEdge(IEdge edge);

}
