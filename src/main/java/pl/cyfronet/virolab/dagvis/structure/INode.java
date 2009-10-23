package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.Shape;

/**
 * A graph node interface.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface INode {
	
	/**
	 * Gets the name of the node which is unique.
	 * 
	 * @return Name.
	 */
	String getName();
	
	/**
	 * Sets the name. If a node of that name exists in graph, the behavior is undefined.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Checks the status of node.
	 * 
	 * @return True if node is active. Otherwise false.
	 */
	boolean isActive();
	
	/**
	 * Sets the status of node.
	 * 
	 * @param h New status.
	 */
	void setActive(boolean h);
	
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

}
