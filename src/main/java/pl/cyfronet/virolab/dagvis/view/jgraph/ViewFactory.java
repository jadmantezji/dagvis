package pl.cyfronet.virolab.dagvis.view.jgraph;

import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewFactory;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;

import pl.cyfronet.virolab.dagvis.view.jgraph.view.EllipseVertexView;

public class ViewFactory implements CellViewFactory {

	@Override
	public CellView createView(GraphModel model, Object cell) {
		CellView view = null;
		if (model.isPort(cell)) {
			view = new PortView(cell);
		} else if (model.isEdge(cell)) {
			view = new EdgeView(cell);
		} else {
			view = new EllipseVertexView(cell);
		}
		return view;
	}
	
}
