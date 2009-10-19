package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.ArrowStyle;
import pl.cyfronet.virolab.dagvis.util.LinePattern;

public interface IEdge {
	
	String getName();
	void setName(String name);
	String getLabel();
	void setLabel(String label);
	Color getColor();
	void setColor(Color color);
	ArrowStyle getArrowStyle();
	void setArrowStyle(ArrowStyle style);
	LinePattern getLinePattern();
	void setLinePattern(LinePattern pattern);
	boolean isBold();
	void setBold(boolean bold);
	INode getSource();
	void setSource(INode source);
	INode getTarget();
	void setTarget(INode target);
	
}
