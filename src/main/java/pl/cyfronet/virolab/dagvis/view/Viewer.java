package pl.cyfronet.virolab.dagvis.view;

import pl.cyfronet.virolab.dagvis.structure.IGraph;

/**
 * Interface for a graph viewer, which displays given graph to the user.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Viewer {
	
	/**
	 * Prepares a graph that user wants to display. 
	 * 
	 * @param in Input graph.
	 */
	void setGraph(IGraph in);
	
	/**
	 * Gets (possibly modified by user interaction) the graph.
	 * 
	 * @return The graph.
	 */
	IGraph getGraph();
	
	/**
	 * Displays the graph to the user.
	 */
	void view();
	
}
