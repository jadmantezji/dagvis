package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.Shape;

public interface INode {
	
	String getName();
	String getLabel();
	Color getColor();
	Shape getShape();
	boolean isBold();

}
