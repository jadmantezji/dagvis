package pl.cyfronet.virolab.dagvis.structure;

import java.util.List;

public interface IGraph {
	
	List<INode> getNodes();
	List<IEdge> getEdges();

}
