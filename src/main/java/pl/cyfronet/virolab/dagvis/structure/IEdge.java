package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.ArrowStyle;

public interface IEdge {
	
	String getName();
	String getLabel();
	Color getColor();
	ArrowStyle getArrowStyle();
	boolean isDotted();
	boolean isBold();
	INode getSource();
	INode getTarget();

}
