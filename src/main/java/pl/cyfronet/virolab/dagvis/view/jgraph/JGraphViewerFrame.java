package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.util.Map;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

public class JGraphViewerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3875542045686949815L;
	
	private JGraph graph;
	private GraphModel model;
	private GraphLayoutCache view;
	
	public JGraphViewerFrame(JGraph graph) {
		this.graph = graph;
		model = graph.getModel();
		view = graph.getGraphLayoutCache();
		applyLayout(new JGraphHierarchicalLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		getContentPane().add(graph);
		//pack();
		setVisible(true);
	}

	private void applyLayout(JGraphLayout layout) {
		JGraphFacade facade = new JGraphFacade(graph);
		layout.run(facade);
		Map nested = facade.createNestedMap(true, true);
		view.edit(nested);
	}
	
}
