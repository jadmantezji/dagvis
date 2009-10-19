package pl.cyfronet.virolab.dagvis.view.jgraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import pl.cyfronet.virolab.dagvis.TransformationException;
import pl.cyfronet.virolab.dagvis.Transformer;
import pl.cyfronet.virolab.dagvis.input.dot.DOTTransformer;
import pl.cyfronet.virolab.dagvis.structure.AbstractGraphEvent;
import pl.cyfronet.virolab.dagvis.structure.GraphEventListener;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.structure.NodeChangedStateEvent;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

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
		JScrollPane scrollPane = new JScrollPane(graph);
		MouseHandler mouseHandler = new MouseHandler(graph);
		for (MouseWheelListener mwl : scrollPane.getMouseWheelListeners()) {
			  scrollPane.removeMouseWheelListener(mwl);
		}
		scrollPane.addMouseWheelListener(mouseHandler);
		getContentPane().add(scrollPane);
		setupMenu();
		//pack();
		setVisible(true);
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
				viewer.getGraph().setNodeActive(nodeName.split(":")[0], false);
			} else if (e.getStateChange() == ItemEvent.SELECTED) {
				if (none) {
					viewer.getGraph().disableAllNodeHighlights();
				} else {
					viewer.getGraph().setNodeActive(nodeName.split(":")[0], true);
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
				CustomGraphConstants.setOpaque(nodeMap, false);
				nested.put(cell, nodeMap);
			}
		} else {
			GraphCell cell = viewer.getNodeCellMapping().get(node);
			Map nodeMap = new HashMap();
			CustomGraphConstants.setOpaque(nodeMap, ncse.isHighlited());
			nested.put(cell, nodeMap);
		}
		view.edit(nested);
	}
	
}
