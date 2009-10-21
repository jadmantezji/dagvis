package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import pl.cyfronet.virolab.dagvis.input.TransformationException;
import pl.cyfronet.virolab.dagvis.input.Transformer;
import pl.cyfronet.virolab.dagvis.input.dot.DOTTransformer;
import pl.cyfronet.virolab.dagvis.structure.AbstractGraphEvent;
import pl.cyfronet.virolab.dagvis.structure.GraphEventListener;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.structure.NodeChangedStateEvent;
import pl.cyfronet.virolab.dagvis.util.CustomColour;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.navigation.GraphNavigator;

public class JGraphViewerFrame extends JFrame implements ItemListener, ActionListener, GraphEventListener {

	private static Logger log = Logger.getLogger(JGraphViewerFrame.class);
	//private static final String DEFAULT_LAYOUT = "Hierarchical";
	private JGraphViewer viewer;
	private JGraph graph;
	private GraphModel model;
	private GraphLayoutCache view;
	private JGraphLayout currentLayout = CustomGraphConstants.layouts.get("Hierarchical");
	
	private JFileChooser chooser = new JFileChooser();
	
	public JGraphViewerFrame(JGraph graph, JGraphViewer viewer) {
		super("DAG Visualizer");
		viewer.getGraph().addGraphEventListener(this);
		this.viewer = viewer;
		this.graph = graph;
		model = graph.getModel();
		view = graph.getGraphLayoutCache();
		
		applyLayout(new JGraphHierarchicalLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setupMenu();
		
		GraphNavigator navigator = GraphNavigator.createInstance(createGraph());
		navigator.setCurrentGraph(graph);
		
		JTree tree = new JTree(new GraphTreeModel(model));
		JScrollPane scrollPane = new JScrollPane(graph);
		JScrollPane scrollPaneTree = new JScrollPane(tree);
		final JSplitPane splitPaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, navigator, scrollPaneTree);
		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneLeft, scrollPane);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				splitPaneLeft.setDividerLocation(0.5);
				splitPane.setDividerLocation(0.3);
			}
		});

		getContentPane().add(splitPane);
		
		MouseHandler mouseHandler = new MouseHandler(graph);
		for (MouseWheelListener mwl : scrollPane.getMouseWheelListeners()) {
			  scrollPane.removeMouseWheelListener(mwl);
		}
		scrollPane.addMouseWheelListener(mouseHandler);
//		pack();
		
		setVisible(true);
	}
	
	private JGraph createGraph() {
		GraphModel model = new DefaultGraphModel();
		GraphLayoutCache layoutCache = new GraphLayoutCache(model, new ViewFactory(), true);
		Set locals = new HashSet();
		locals.add(GraphConstants.BOUNDS);
		layoutCache.setLocalAttributes(locals);
		return new JGraph(model, layoutCache);
	}
	
	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		JMenuItem open = new JMenuItem("Open...");
		open.addActionListener(this);
		JMenuItem save = new JMenuItem("Save...");
		save.addActionListener(this);
		menuFile.add(open);
		menuFile.add(save);
		FileNameExtensionFilter dotFilter = new FileNameExtensionFilter("Graphviz DOT file", "dot");
		FileNameExtensionFilter yamlFilter = new FileNameExtensionFilter("Graph in YAML format", "yaml");
		chooser.addChoosableFileFilter(dotFilter);
		chooser.addChoosableFileFilter(yamlFilter);
		chooser.setFileFilter(yamlFilter);
		
		JMenu menuLayout = new JMenu("Layout");
		ButtonGroup group = new ButtonGroup();
		for (Entry<String, JGraphLayout> entry : CustomGraphConstants.layouts.entrySet()) {
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(entry.getKey());
			group.add(menuItem);
			menuLayout.add(menuItem);
			menuItem.addItemListener(this);
			if (entry.getValue() == currentLayout) {
				menuItem.setSelected(true);
			}
		}
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(this);
		
		JComboBox combo = new JComboBox();
		combo.addItemListener(this);
		combo.addItem("None");
		combo.setMaximumRowCount(50);
		for (INode node : viewer.getGraph().getNodes()) {
			combo.addItem(node.getName() + ": label(" + node.getLabel() + ")");
		}
		
		menuBar.add(menuFile);
		menuBar.add(menuLayout);
		menuBar.add(combo);
		menuBar.add(exit);
		setJMenuBar(menuBar);
	}

	private void applyLayout(JGraphLayout layout) {
		JGraphFacade facade = new JGraphFacade(graph);
		facade.findTreeRoots();
		layout.run(facade);
		Map nested = facade.createNestedMap(true, true);
		view.edit(nested);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() instanceof JRadioButtonMenuItem) {	// 
			String text = ((JRadioButtonMenuItem) e.getSource()).getText();
			log.debug("Layout changed to: " + text);
			applyLayout(CustomGraphConstants.layouts.get(text));
		} else if (e.getSource() instanceof JComboBox) {
			String nodeName = e.getItem().toString();
			boolean none = nodeName.equals("None");
			if (e.getStateChange() == ItemEvent.DESELECTED && !none) {
				viewer.getGraph().setNodeState(nodeName.split(":")[0], false);
			} else if (e.getStateChange() == ItemEvent.SELECTED) {
				if (none) {
					viewer.getGraph().deactivateAllNodes();
				} else {
					viewer.getGraph().setNodeState(nodeName.split(":")[0], true);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		log.trace("Event: " + e);
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Open...")) {
			int returnValue = chooser.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				// need to create TransformerFactory in order to
				// decouple from JPGD IO implementation
				Transformer t = new DOTTransformer();
				IGraph graph = null;
				try {
					graph = t.getGraph(new FileInputStream(chooser.getSelectedFile()));
				} catch (FileNotFoundException e1) {
					log.error("File not found", e1);
					return;
				} catch (TransformationException e1) {
					log.error("Malformed file or parser error", e1);
					return;
				}
				view.remove(view.getCells(true, true, true, true));
				viewer.setGraph(graph);
				log.trace("Applying current layout: " + currentLayout);
				applyLayout(currentLayout);
			}
		}
	}

	public void stateChanged(AbstractGraphEvent e) {
		log.debug(e);
		NodeChangedStateEvent ncse = (NodeChangedStateEvent) e;
		INode node = ncse.getNode();
		Map nested = new HashMap();
		if (node == null) {
			for (GraphCell cell : viewer.getNodeCellMapping().values()) {
				Map nodeMap = new HashMap();
				CustomGraphConstants.setBackground(nodeMap, CustomColour.LIGHT_RED);
				CustomGraphConstants.setOpaque(nodeMap, false);
				nested.put(cell, nodeMap);
			}
		} else {
			GraphCell cell = viewer.getNodeCellMapping().get(node);
			Map nodeMap = new HashMap();
			CustomGraphConstants.setBackground(nodeMap, CustomColour.LIGHT_RED);
			CustomGraphConstants.setOpaque(nodeMap, ncse.isHighlited());
			nested.put(cell, nodeMap);
		}
		view.edit(nested);
	}
	
}
