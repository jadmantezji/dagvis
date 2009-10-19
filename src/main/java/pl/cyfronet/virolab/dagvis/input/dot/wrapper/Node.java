package pl.cyfronet.virolab.dagvis.input.dot.wrapper;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.structure.GraphEventListener;
import pl.cyfronet.virolab.dagvis.util.CustomColour;
import pl.cyfronet.virolab.dagvis.util.Shape;
import pl.cyfronet.virolab.dagvis.util.StringUtil;

import com.alexmerz.graphviz.objects.Id;

public class Node implements INode {

	private com.alexmerz.graphviz.objects.Node internal;
	
	public Node() {
		internal = new com.alexmerz.graphviz.objects.Node();
	}
	
	public Node(com.alexmerz.graphviz.objects.Node internal) {
		this.internal = internal;
		if (internal.getAttribute("labelProcessed") == null && getLabel() != null) {
			internal.setAttribute("label", StringUtil.processLabel(getLabel()));
			internal.setAttribute("labelProcessed", new String());
		}
	}
	
	public String getName() {
		Id id = internal.getId();
		return id.getId().isEmpty() ? id.getLabel() : id.getId();
	}
	
	public Color getColor() {
		return CustomColour.parse(internal.getAttribute("color"));
	}

	public String getLabel() {
		return internal.getAttribute("label");
	}

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
		return getLabel() == null || getLabel().isEmpty() ? getName() : getLabel();
	}

	public boolean isBold() {
		if (internal.getAttribute("style") != null && internal.getAttribute("style").equals("bold")) {
			return true;
		}
		return false;
	}

	public void setBold(boolean bold) {
		// TODO Auto-generated method stub
		
	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setShape(Shape shape) {
		// TODO Auto-generated method stub
		
	}

	public int getContourCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setContourCount(int count) {
		// TODO Auto-generated method stub
		
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setActive(boolean h) {
		// TODO Auto-generated method stub
		
	}
	
}
