package pl.cyfronet.virolab.dagvis.structure;

import java.util.Collection;

/**
 * An interface of graph. Allows access to all the nodes and edges and provides
 * infrastructure to handle listeners. 
 * 
 * @author Krzysztof Nirski
 *
 */
public interface IGraph {
	
	/**
	 * Gets all the nodes in the graph.
	 * 
	 * @return Collection of nodes.
	 */
	Collection<INode> getNodes();
	
	/**
	 * Gets all the edges in the graph.
	 * 
	 * @return Collection of edges.
	 */
	Collection<IEdge> getEdges();
	
	/**
	 * Adds a node to graph.
	 * 
	 * @param node
	 */
	void addNode(INode node);
	
	/**
	 * Adds an edge to graph.
	 * 
	 * @param edge
	 */
	void addEdge(IEdge edge);
	
	/**
	 * Gets a node by its name. Node names are unique.
	 * 
	 * @param name
	 * @return Desired node or null if it does not exist.
	 */
	INode getNodeByName(String name);
	
	/**
	 * Sets all the nodes statuses to disabled. Notifies listeners.
	 */
	void deactivateAllNodes();
	
	/**
	 * Set status for particular node. Notifies listeners.
	 * 
	 * @param nodeName 
	 * @param active
	 */
	void setNodeState(String nodeName, boolean active);
	
	/**
	 * Adds a graph event listener.
	 * 
	 * @param listener
	 */
	void addGraphEventListener(GraphEventListener listener);
	
	/**
	 * Removes a listener.
	 *
	 * @param listener
	 */
	void removeGraphEventListener(GraphEventListener listener);
	
	/**
	 * Gets all listeners.
	 * 
	 * @return An array of listeners.
	 */
	GraphEventListener[] getGraphEventListeneres();

}
