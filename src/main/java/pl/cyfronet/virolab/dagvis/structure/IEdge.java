package pl.cyfronet.virolab.dagvis.structure;

import java.awt.Color;

import pl.cyfronet.virolab.dagvis.util.ArrowStyle;
import pl.cyfronet.virolab.dagvis.util.LinePattern;

/**
 * A graph edge interface.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface IEdge {
	
	/**
	 * Gets the name of the edge which is unique.
	 * 
	 * @return Name.
	 */
	String getName();
	
	/**
	 * Sets the name. If an edge of that name exists in graph, the behavior is undefined.
	 * 
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * Gets the source node.
	 * 
	 * @return Source node.
	 */
	INode getSource();
	
	/**
	 * Sets the source node.
	 *  
	 * @param source Source node.
	 */
	void setSource(INode source);
	
	/**
	 * Gets the target node.
	 * 
	 * @return Target node.
	 */
	INode getTarget();
	
	/**
	 * Sets the target node.
	 *  
	 * @param source Target node.
	 */
	void setTarget(INode target);
	
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
	
}
