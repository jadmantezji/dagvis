package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import com.jgraph.layout.JGraphCompoundLayout;
import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.layout.tree.JGraphCompactTreeLayout;
import com.jgraph.layout.tree.JGraphTreeLayout;

public class JGraphViewerFrame extends JFrame implements ItemListener {

	private static Logger log = Logger.getLogger(JGraphViewerFrame.class);
	public static final String[] layoutNames = { "Hierarchical", "CompactTree", "Tree", "Compound", "FastOrganic", "FR", "Organic", "Annealing" };
	private static final int DEFAULT_LAYOUT = 0;
	private JGraph graph;
	private GraphModel model;
	private GraphLayoutCache view;
	
	public JGraphViewerFrame(JGraph graph) {
		super("DAG Visualizer");
		this.graph = graph;
		model = graph.getModel();
		view = graph.getGraphLayoutCache();
		applyLayout(new JGraphHierarchicalLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		getContentPane().add(new JScrollPane(graph));
		setupMenu();
		//pack();
		setVisible(true);
	}
	
	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuLayout = new JMenu("Layout");
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < layoutNames.length; i++) {
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(layoutNames[i]);
			group.add(menuItem);
			menuLayout.add(menuItem);
			menuItem.addItemListener(this);
			if (i == DEFAULT_LAYOUT) {
				menuItem.setSelected(true);
			}
		}
		menuBar.add(menuLayout);
		setJMenuBar(menuBar);
	}

	private void applyLayout(JGraphLayout layout) {
		JGraphFacade facade = new JGraphFacade(graph);
		facade.findTreeRoots();
		layout.run(facade);
		Map nested = facade.createNestedMap(true, true);
		view.edit(nested);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() instanceof JRadioButtonMenuItem) {
			String text = ((JRadioButtonMenuItem) e.getSource()).getText();
			log.debug("Layout changed to: " + text);
			if (text.equals("Hierarchical")) {
				applyLayout(new JGraphHierarchicalLayout());
			} else if (text.equals("CompactTree")) {
				applyLayout(new JGraphCompactTreeLayout());
			} else if (text.equals("Tree")) {
				applyLayout(new JGraphTreeLayout());
			} else if (text.equals("Compound")) {
				applyLayout(new JGraphCompoundLayout());
			} else if (text.equals("KKLayout")) {
				
			}
		}
	}
	
}
