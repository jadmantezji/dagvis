package pl.cyfronet.virolab.dagvis.input.yaml.wrapper;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.ArrowStyle;
import pl.cyfronet.virolab.dagvis.util.CustomColour;
import pl.cyfronet.virolab.dagvis.util.LinePattern;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class Edge implements IEdge {
	
	private String name;
	private String label;
	private LinePattern pattern;
	private INode source;
	private INode target;
	
	public Edge(String name) {
		this.name = name;
	}

	public ArrowStyle getArrowStyle() {
		return ArrowStyle.NORMAL;
	}

	public Color getColor() {
		return CustomColour.BLACK;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public INode getSource() {
		return source;
	}

	public INode getTarget() {
		return target;
	}

	public boolean isBold() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setSource(INode source) {
		this.source = source;
	}

	public void setTarget(INode target) {
		this.target = target;
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
		return label;
	}

	public void setArrowStyle(ArrowStyle style) {
		// TODO Auto-generated method stub
		
	}

	public void setBold(boolean bold) {
		// TODO Auto-generated method stub
		
	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public LinePattern getLinePattern() {
		return pattern;
	}

	public void setLinePattern(LinePattern pattern) {
		this.pattern = pattern;
	}
	
}
