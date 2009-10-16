package pl.cyfronet.virolab.dagvis.jpgd.wrapper;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.ArrowStyle;
import pl.cyfronet.virolab.dagvis.util.CustomColour;

public class Edge implements IEdge {

	private com.alexmerz.graphviz.objects.Edge internal;

	public Edge() {
		internal = new com.alexmerz.graphviz.objects.Edge();;
	}
	
	public Edge(com.alexmerz.graphviz.objects.Edge internal) {
		this.internal = internal;
	}
	
	public ArrowStyle getArrowStyle() {
		return ArrowStyle.parse(internal.getAttribute("arrowhead"));
	}

	public String getName() {
		return internal.getSource().getNode().getId().getId() + internal.getTarget().getNode().getId().getId();
	}
	
	public Color getColor() {
		return CustomColour.parse(internal.getAttribute("color"));
	}

	public String getLabel() {
		return internal.getAttribute("label");
	}

	public boolean isBold() {
		if (internal.getAttribute("style") != null && internal.getAttribute("style").equals("bold")) {
			return true;
		}
		return false;
	}

	public boolean isDotted() {
		if (internal.getAttribute("style") != null && internal.getAttribute("style").equals("dotted")) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return getName().equals(obj);
	}
	
	@Override
	public String toString() {
		//return getLabel() == null || getLabel().isEmpty() ? getName() : getLabel();
		return getLabel();
	}

	public INode getSource() {
		return new Node(internal.getSource().getNode());
	}

	public INode getTarget() {
		return new Node(internal.getTarget().getNode());
	}

}
