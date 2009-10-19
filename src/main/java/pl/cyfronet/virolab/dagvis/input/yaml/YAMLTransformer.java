package pl.cyfronet.virolab.dagvis.input.yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import pl.cyfronet.virolab.dagvis.TransformationException;
import pl.cyfronet.virolab.dagvis.Transformer;
import pl.cyfronet.virolab.dagvis.input.yaml.wrapper.Edge;
import pl.cyfronet.virolab.dagvis.input.yaml.wrapper.Graph;
import pl.cyfronet.virolab.dagvis.input.yaml.wrapper.Node;
import pl.cyfronet.virolab.dagvis.structure.IEdge;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.structure.INode;
import pl.cyfronet.virolab.dagvis.util.LinePattern;
import pl.cyfronet.virolab.dagvis.util.Shape;

public class YAMLTransformer implements Transformer {

	private static Logger log = Logger.getLogger(YAMLTransformer.class);

	public static final String NODES = "nodes";
	public static final String EDGES = "edges";
	public static final String NODE_LABEL = "name";
	public static final String NODE_TYPE = "type";
	public static final String EDGE_LABEL = "deps";
	public static final String EDGE_TYPE = "type";
	
	private Properties edgeTypes;
	private Properties nodeTypes;
	
	private Map<Integer, IEdge> createdEdges = new HashMap<Integer, IEdge>();
	private Map readNodes;
	private Map readEdges;
	
	public YAMLTransformer() {
		edgeTypes = new Properties();
		nodeTypes = new Properties();
		try {
			edgeTypes.load(this.getClass().getResourceAsStream("/edge-types.properties"));
			nodeTypes.load(this.getClass().getResourceAsStream("/node-types.properties"));
		} catch (IOException e) {
			log.error("Failed to read resources", e);
			System.exit(1);
		}
	}

	public IGraph getGraph(InputStream source) throws TransformationException {
		Yaml yaml = new Yaml();
		Map map = (Map) yaml.load(source);
		log.debug("YAML read: " + map);
		readNodes = (Map) map.get(NODES);
		readEdges = (Map) map.get(EDGES);
		IGraph graph = new Graph();
		for (Object o: readNodes.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			Integer name = (Integer) entry.getKey();
			Map value = (Map) entry.getValue();
			INode node = createNode(name, value);
			graph.addNode(node);
		}
		for (IEdge edge : createdEdges.values()) {
			graph.addEdge(edge);
		}
		return graph;
	}
	
	private INode createNode(Integer name, Map attributes) {
		INode node = new Node(name.toString());
		List<Integer> input = (List<Integer>) attributes.get("input");
		List<Integer> output = (List<Integer>) attributes.get("output");
		for (Integer edgeName : input) {
			if (createdEdges.containsKey(edgeName)) {
				createdEdges.get(edgeName).setTarget(node);
			} else {
				IEdge edge = createEdge(edgeName, (Map) readEdges.get(edgeName));
				edge.setTarget(node);
			}
		}
		for (Integer edgeName : output) {
			if (createdEdges.containsKey(edgeName)) {
				createdEdges.get(edgeName).setSource(node);
			} else {
				IEdge edge = createEdge(edgeName, (Map) readEdges.get(edgeName));
				edge.setSource(node);
			}
		}
		
		node.setLabel((String) attributes.get(NODE_LABEL));
		Shape shape = Shape.parse(nodeTypes.getProperty(attributes.get(NODE_TYPE) + ".shape"));
		node.setShape(shape);
		log.trace(shape);
		int contourCount = 1;
		try {
			contourCount = Integer.parseInt(nodeTypes.getProperty(attributes.get(NODE_TYPE) + ".contour_count"));
		} catch(NumberFormatException e) {
			log.trace("contourCount of " + node + " defaults to " + contourCount);
		}
		node.setContourCount(contourCount);
		return node;
	}
	
	private IEdge createEdge(Integer name, Map attributes) {
		IEdge edge = new Edge(name.toString());
		edge.setLabel((String) attributes.get(EDGE_LABEL));
		edge.setLinePattern(LinePattern.parse(edgeTypes.getProperty(attributes.get(EDGE_TYPE) + ".line_pattern")));
		createdEdges.put(name, edge);
		return edge;
	}
	
}
