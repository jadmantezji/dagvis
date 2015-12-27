First of all, it is important to know the interfaces of data structures. The package that includes these interfaces is **pl.cyfronet.virolab.dagvis.structure**.

In order to use the API, we need to obtain **pl.cyfronet.virolab.dagvis.structure.IGraph** instance, which can be done by external thread call on **pl.cyfronet.virolab.dagvis.DagViewer** object.

Data structure interfaces:

```
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
```

```
/**
 * A graph node interface.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface INode {
	
	/**
	 * Gets the name of the node which is unique.
	 * 
	 * @return Name.
	 */
	String getName();
	
	/**
	 * Sets the name. If a node of that name exists in graph, the behavior is undefined.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Checks the status of node.
	 * 
	 * @return True if node is active. Otherwise false.
	 */
	boolean isActive();
	
	/**
	 * Sets the status of node.
	 * 
	 * @param h New status.
	 */
	void setActive(boolean h);
	
	String getLabel();
	void setLabel(String label);
	Color getColor();
	void setColor(Color color);
	Shape getShape();
	void setShape(Shape shape);
	int getContourCount();
	void setContourCount(int count);
	boolean isBold();
	void setBold(boolean bold);

}
```

There are two interfaces that are vital for using the event API. The first one: **pl.cyfronet.virolab.dagvis.structure.IGraph**, which includes methods for adding and removing listeners. The second one is listener interface itself.

```
/**
 * A listener interface to graph state events.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface GraphEventListener {

	/**
	 * Called when an event occured.
	 * @param e Graph event.
	 */
	void stateChanged(AbstractGraphEvent e);

}
```

The listener interface has to be implemented by view in the graph MVC framework. In dagvis project, it is done by **pl.cyfronet.virolab.dagvis.JGraphFrame** class, which is the main UI window at the same time. User can implement a listener on its own, but not necessarily. It is important to remember that there is always at least the main UI related listener.  Please refer to the sequence diagram below:

![http://dagvis.googlecode.com/hg/uml/pics/listener.png](http://dagvis.googlecode.com/hg/uml/pics/listener.png)

Currently only one event is supported, it is **pl.cyfronet.virolab.dagvis.structure.NodeChangedStateEvent**

```
/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class NodeChangedStateEvent extends AbstractGraphEvent {
	
	
	/**
	 * When node is null, deactivate all nodes.
	 * 
	 * @param node
	 */
	public NodeChangedStateEvent(INode node) { ... }
	
	public NodeChangedStateEvent(INode node, boolean active) { ... }
	
	public INode getNode() { ... }
	public boolean isHighlited() { ... }
	
}
```