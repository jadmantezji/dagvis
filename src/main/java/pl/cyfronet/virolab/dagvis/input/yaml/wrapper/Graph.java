package pl.cyfronet.virolab.dagvis.input.yaml.wrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.cyfronet.virolab.dagvis.structure.AbstractGraph;
import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.INode;

public class Graph extends AbstractGraph {
	
	private Map<String, INode> nodes = new HashMap<String, INode>();
	private Map<String, IEdge> edges = new HashMap<String, IEdge>();

	public Collection<IEdge> getEdges() {
		return edges.values();
	}

	public Collection<INode> getNodes() {
		return nodes.values();
	}
	
	public INode getNodeByName(String name) {
		return nodes.get(name);
	}

	public void addEdge(IEdge edge) {
		edges.put(edge.getName(), edge);
	}

	public void addNode(INode node) {
		nodes.put(node.getName(), node);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nodes:\n");
		for (INode node : nodes.values()) {
			sb.append(node + "\n");
		}
		sb.append("Edges:\n");
		for (IEdge edge : edges.values()) {
			sb.append(edge.getName());
			sb.append(": ");
			sb.append("source=" + edge.getSource() + ", ");
			sb.append("target=" + edge.getTarget() + "\n");
		}
		return sb.toString();
	}
	
}
