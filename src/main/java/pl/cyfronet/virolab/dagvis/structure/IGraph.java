package pl.cyfronet.virolab.dagvis.structure;

import java.util.Collection;

public interface IGraph {
	
	Collection<INode> getNodes();
	Collection<IEdge> getEdges();
	void addNode(INode node);
	void addEdge(IEdge edge);
	INode getNodeByName(String name);
	
	void disableAllNodeHighlights();
	void setNodeActive(String nodeName, boolean active);
	void addGraphEventListener(GraphEventListener listener);
	void removeGraphEventListener(GraphEventListener listener);
	GraphEventListener[] getGraphEventListeneres();

}
