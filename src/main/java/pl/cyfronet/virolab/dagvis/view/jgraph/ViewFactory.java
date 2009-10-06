package pl.cyfronet.virolab.dagvis.view.jgraph;

import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;
import org.jgraph.graph.VertexView;

import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.Shape;
import pl.cyfronet.virolab.dagvis.view.jgraph.view.EllipseVertexView;

public class ViewFactory implements CellViewFactory {

	@Override
	public CellView createView(GraphModel model, Object cell) {
		CellView view = null;
		GraphCell gc = (GraphCell) cell;
		if (model.isPort(cell)) {
			view = new PortView(cell);
		} else if (model.isEdge(cell)) {
			view = new EdgeView(cell);
		} else {
			Shape shape = ((INode) ((DefaultGraphCell) cell).getUserObject()).getShape();
			switch (shape) {
				case BOX : view = new VertexView(cell); break;
				case ELLIPSE : view = new EllipseVertexView(cell); break;
			}
		}
		return view;
	}
	
}
