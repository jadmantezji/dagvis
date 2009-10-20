package pl.cyfronet.virolab.dagvis.view.jgraph;

import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;

import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.Shape;
import pl.cyfronet.virolab.dagvis.view.jgraph.view.BoxVertexView;
import pl.cyfronet.virolab.dagvis.view.jgraph.view.EllipseVertexView;
import pl.cyfronet.virolab.dagvis.view.jgraph.view.TriangleVertexView;

public class ViewFactory implements CellViewFactory {

	public CellView createView(GraphModel model, Object cell) {
		CellView view = null;
		if (model.isPort(cell)) {
			view = new PortView(cell);
		} else if (model.isEdge(cell)) {
			view = new EdgeView(cell);
		} else {
			INode node = (INode) ((DefaultGraphCell) cell).getUserObject();
			Shape shape = node.getShape();
			switch (shape) {
				case BOX : view = new BoxVertexView(cell); break;
				case CIRCLE : view = new EllipseVertexView(cell, node.getContourCount()); break;
				case ELLIPSE : view = new EllipseVertexView(cell); break;
				case TRIANGLE : view = new TriangleVertexView(cell); break;
			}
		}
		return view;
	}
	
}
