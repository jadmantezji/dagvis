package pl.cyfronet.virolab.dagvis.jpgd.wrapper;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.CustomColour;
import pl.cyfronet.virolab.dagvis.util.Shape;

public class Node implements INode {

	private com.alexmerz.graphviz.objects.Node internal = new com.alexmerz.graphviz.objects.Node();
	
	public Node(com.alexmerz.graphviz.objects.Node internal) {
		this.internal = internal;
	}
	
	public String getName() {
		return internal.getId().getId();
	}
	
	@Override
	public Color getColor() {
		return CustomColour.parse(internal.getAttribute("color"));
	}

	@Override
	public String getLabel() {
		return internal.getAttribute("label");
	}

	@Override
	public Shape getShape() {
		return Shape.parse(internal.getAttribute("shape"));
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
		return getLabel();
	}

	@Override
	public boolean isBold() {
		if (internal.getAttribute("style") != null && internal.getAttribute("style").equals("bold")) {
			return true;
		}
		return false;
	}
	
}
