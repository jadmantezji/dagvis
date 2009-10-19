package pl.cyfronet.virolab.dagvis.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractGraph implements IGraph {

	private List<GraphEventListener> listeners = new ArrayList<GraphEventListener>();
	
	public abstract Collection<INode> getNodes();
	public abstract Collection<IEdge> getEdges();
	public abstract INode getNodeByName(String name);
	public abstract void addNode(INode node);
	public abstract void addEdge(IEdge edge);

	public void addGraphEventListener(GraphEventListener listener) {
		listeners.add(listener);
	}

	public void removeGraphEventListener(GraphEventListener listener) {
		listeners.remove(listener);
	}

	public GraphEventListener[] getGraphEventListeneres() {
		return listeners.toArray(new GraphEventListener[] {});
	}

	public void disableAllNodeHighlights() {
		for (INode node : getNodes()) {
			node.setActive(false);
		}
		for (GraphEventListener e : listeners) {
			e.stateChanged(new NodeChangedStateEvent(null));
		}
	}

	public void setNodeActive(String nodeName, boolean h) {
		INode node = getNodeByName(nodeName);
		node.setActive(h);
		for (GraphEventListener e : listeners) {
			e.stateChanged(new NodeChangedStateEvent(node, h));
		}
	}

}
