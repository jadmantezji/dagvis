package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.Shape;

public interface INode {
	
	String getName();
	void setName(String name);
	String getLabel();
	void setLabel(String label);
	Color getColor();
	void setColor(Color color);
	Shape getShape();
	void setShape(Shape shape);
	int getContourCount();
	void setContourCount(int count);
	boolean isBold();
	void setBold(boolean bold);
	boolean isHighlighted();
	void setHighlighted(boolean h);
	void addStateListener(NodeStateListener listener);
	void removeStateListener(NodeStateListener listener);
	NodeStateListener[] getNodeStateListeneres();
	
}
