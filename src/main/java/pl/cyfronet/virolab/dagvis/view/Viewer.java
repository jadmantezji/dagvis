package pl.cyfronet.virolab.dagvis.view;

import pl.cyfronet.virolab.dagvis.structure.IGraph;

public interface Viewer {
	
	void setGraph(IGraph in);
	void view();
	
}
