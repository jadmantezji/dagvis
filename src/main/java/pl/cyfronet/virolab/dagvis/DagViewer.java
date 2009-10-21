package pl.cyfronet.virolab.dagvis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import pl.cyfronet.virolab.dagvis.input.TransformationException;
import pl.cyfronet.virolab.dagvis.input.Transformer;
import pl.cyfronet.virolab.dagvis.input.dot.DOTTransformer;
import pl.cyfronet.virolab.dagvis.input.yaml.YAMLTransformer;
import pl.cyfronet.virolab.dagvis.structure.IGraph;
import pl.cyfronet.virolab.dagvis.view.Viewer;
import pl.cyfronet.virolab.dagvis.view.jgraph.JGraphViewer;

/**
 * Entry class
 * 
 * @author Krzysztof Nirski
 *
 */

public class DagViewer {
	
	private static Logger log = Logger.getLogger(DagViewer.class);
	
	private static final String classNameToReplace = "ClassName";

	private static IGraph g;
	
	public static void printHelp() throws IOException {
		InputStream usageStream = DagViewer.class.getResourceAsStream("/usage.txt");
		StringBuilder sb = new StringBuilder();
		byte[] buffer = new byte[4096];
		while(usageStream.read(buffer) != -1) {
			sb.append(new String(buffer));
		}
		System.out.println(sb.toString().replace(classNameToReplace, DagViewer.class.getName()));
	}
	
	public static IGraph getGraphInstance() {
		return g;
	}
	
	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure(DagViewer.class.getResource("/log4j.properties"));
		InputStream source = null;
		try {
			source = new FileInputStream(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			printHelp();
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " not found!");
			printHelp();
			System.exit(1);
		}
		Transformer t = null;
		if (args[0].endsWith(".dot")) {
			t = new DOTTransformer();
		} else if (args[0].endsWith(".yaml")) {
			t = new YAMLTransformer();
		}
		try {
			g = (IGraph) t.getGraph(source);
		} catch (TransformationException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
		log.debug(g);
		// TODO
		// There should be a possibility to choose in a declarative manner
		// (for example: in configuration file) which implementation of
		// Viewer interface do we want to use.
		// A heavy-weight solution would be to use Spring Inverse of control container
		Viewer v = new JGraphViewer();
		v.setGraph(g);
		v.view();
	}
	
}
