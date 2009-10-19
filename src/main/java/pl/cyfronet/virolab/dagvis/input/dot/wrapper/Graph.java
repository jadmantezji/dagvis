package pl.cyfronet.virolab.dagvis.input.dot.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.cyfronet.virolab.dagvis.structure.AbstractGraph;
import pl.cyfronet.virolab.dagvis.structure.GraphEventListener;
import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.INode;

public class Graph extends AbstractGraph {

	private com.alexmerz.graphviz.objects.Graph internal; 
	
	public Graph() {
		internal = new com.alexmerz.graphviz.objects.Graph();
	}
	
	public Graph(com.alexmerz.graphviz.objects.Graph internal) {
		this.internal = internal;
	}
	
	public Collection<INode> getNodes() {
		List<INode> nodes = new ArrayList<INode>();
		for (com.alexmerz.graphviz.objects.Node n : internal.getNodes(true)) {
			nodes.add(new Node(n));
		}
		return nodes;
	}
	
	public Collection<IEdge> getEdges() {
		List<IEdge> edges = new ArrayList<IEdge>();
		for (com.alexmerz.graphviz.objects.Edge e : internal.getEdges()) {
			edges.add(new Edge(e));
		}
		return edges;
	}
	
	@Override
	public String toString() {
		return internal.toString();
	}

	public void addEdge(IEdge edge) {
		// TODO Auto-generated method stub
		
	}

	public void addNode(INode node) {
		// TODO Auto-generated method stub
		
	}

	public INode getNodeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
