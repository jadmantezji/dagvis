package pl.cyfronet.virolab.dagvis.view;

import pl.cyfronet.virolab.dagvis.structure.IGraph;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Viewer {
	
	void setGraph(IGraph in);
	IGraph getGraph();
	void view();
	
}
