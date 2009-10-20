package pl.cyfronet.virolab.dagvis.view.jung;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.view.jung.transformers.FillPaintTransformer;
import pl.cyfronet.virolab.dagvis.view.jung.transformers.NodeLabeller;
import pl.cyfronet.virolab.dagvis.view.jung.transformers.PaintTransformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class JungViewerFrame extends JFrame implements ItemListener {

	private static Logger log = Logger.getLogger(JungViewerFrame.class);
	private static final String[] layoutNames = { "CircleLayout", "FRLayout", "FRLayout2", "ISOMLayout", "KKLayout" };   
	private static final int defaultLayout = 1;
	private static final long serialVersionUID = -1601914651657821233L;
	
	private Graph<INode, IEdge> graph;
	private VisualizationViewer<INode, IEdge> vv;
	private JMenuBar menuBar = new JMenuBar();
	
	public JungViewerFrame(Graph<INode, IEdge> graph) {
		super("DAG Visualizer");
		this.graph = graph;
		Layout<INode, IEdge> layout = new FRLayout<INode, IEdge>(graph);
		vv = new VisualizationViewer<INode, IEdge>(layout);
		setupRenderContext();
		JMenu menu = new JMenu("Layout");
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < layoutNames.length; i++) {
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(layoutNames[i]);
			group.add(menuItem);
			menu.add(menuItem);
			menuItem.addItemListener(this);
			if (i == defaultLayout) {
				menuItem.setSelected(true);
			}
		}
		menuBar.add(menu);
		// default layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		getContentPane().add(vv);
		pack();
		setVisible(true);
	}
	
	private void setupRenderContext() {
		vv.getRenderContext().setVertexLabelTransformer(new NodeLabeller());
		vv.getRenderContext().setVertexDrawPaintTransformer(new PaintTransformer());
		vv.getRenderContext().setVertexFillPaintTransformer(new FillPaintTransformer());
		System.out.println(vv.getRenderContext().getGraphicsContext());
//		FontMetrics metrics = vv.getRenderContext().getGraphicsContext().getDelegate().getFontMetrics();
//		vv.getRenderContext().setVertexShapeTransformer(new ShapeTransformer(metrics));
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() instanceof JRadioButtonMenuItem) {
			String text = ((JRadioButtonMenuItem) e.getSource()).getText();
			log.debug("Layout changed to: " + text);
			if (text.equals("CircleLayout")) {
				vv.setGraphLayout(new CircleLayout<INode, IEdge>(graph));
			} else if (text.equals("FRLayout")) {
				vv.setGraphLayout(new FRLayout<INode, IEdge>(graph));
			} else if (text.equals("FRLayout2")) {
				vv.setGraphLayout(new FRLayout2<INode, IEdge>(graph));
			} else if (text.equals("ISOMLayout")) {
				vv.setGraphLayout(new ISOMLayout<INode, IEdge>(graph));
			} else if (text.equals("KKLayout")) {
				vv.setGraphLayout(new KKLayout<INode, IEdge>(graph));
			}
		}
	}

	
}
