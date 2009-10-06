package pl.cyfronet.virolab.dagvis.jpgd.wrapper;

import java.util.ArrayList;
import java.util.List;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;

public class Graph implements IGraph {

	private com.alexmerz.graphviz.objects.Graph internal; 
	
	public Graph() {
		internal = new com.alexmerz.graphviz.objects.Graph();
	}
	
	public Graph(com.alexmerz.graphviz.objects.Graph internal) {
		this.internal = internal;
	}
	
	public List<INode> getNodes() {
		List<INode> nodes = new ArrayList<INode>();
		for (com.alexmerz.graphviz.objects.Node n : internal.getNodes(true)) {
			nodes.add(new Node(n));
		}
		System.out.println(internal.getAttributes());
		return nodes;
	}
	
	public List<IEdge> getEdges() {
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

}
